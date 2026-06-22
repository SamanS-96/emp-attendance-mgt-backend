package edu.icet.ecom.service.attendance.impl;

import edu.icet.ecom.entity.Attendance;
import edu.icet.ecom.model.dto.request.CheckInCreationRequest;
import edu.icet.ecom.model.dto.request.CheckOutCreationRequest;
import edu.icet.ecom.model.dto.response.AttendanceResponse;
import edu.icet.ecom.model.dto.response.TodayAttendance;
import edu.icet.ecom.repository.attendance.AttendanceRepository;
import edu.icet.ecom.repository.employee.EmployeeRepository;
import edu.icet.ecom.repository.leave.LeaveRepository;
import edu.icet.ecom.service.attendance.AttendanceService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;
    private final LeaveRepository leaveRepository;

    @Override
    @Transactional
    public String saveCheckIn(CheckInCreationRequest checkInCreationRequest) {

        if (leaveRepository.isOnLeaveToday(employeeRepository.getUser(checkInCreationRequest.getUserName()).getEmployeeId())){
            return "You're On Leave Today, Can't Check In !";
        }

        if (LocalTime.now().isAfter(LocalTime.of(13,0))){
            Boolean isCheckedIn = attendanceRepository.isCheckedIn(checkInCreationRequest.getUserName());
            return isCheckedIn ? "You Are Allready Checked In !" : "You Can't Check In After 1:00 pm !";
        }

        if (LocalTime.now().isAfter(LocalTime.of(8,30)) && LocalTime.now().isBefore(LocalTime.of(13,0))){
            Boolean isSaved = attendanceRepository.saveCheckIn(checkInCreationRequest);
            attendanceRepository.setStatusForHalfday(employeeRepository.getUser(checkInCreationRequest.getUserName()).getEmployeeId());
            return isSaved ? "Check In Successful, Thankyou !" : "You Are Allready Checked In !";
        }

        Boolean isSaved = attendanceRepository.saveCheckIn(checkInCreationRequest);
        return isSaved ? "Check In Successful, Thankyou !" : "You Are Allready Checked In !";
    }

    @Override
    @Transactional
    public String saveCheckOut(CheckOutCreationRequest checkOutCreationRequest) {

        if(!attendanceRepository.isCheckedIn(checkOutCreationRequest.getUserName())){
            return "You're Not Checked In yet !";
        }

        if (LocalTime.now().isBefore(LocalTime.of(13,0))){
            return "You Cant Check Out Early ! (Before 1:00 pm)";
        }

        if (LocalTime.now().isAfter(LocalTime.of(13,0)) && LocalTime.now().isBefore(LocalTime.of(17,0))){
            Boolean isSaved = attendanceRepository.saveCheckOut(checkOutCreationRequest);
            attendanceRepository.setStatusForHalfday(employeeRepository.getUser(checkOutCreationRequest.getUserName()).getEmployeeId());
            return isSaved ? "Check Out Successful, Thankyou !" : "You Are Allready Checked Out !";
        }

        Boolean isSaved = attendanceRepository.saveCheckOut(checkOutCreationRequest);
        return isSaved ? "Check Out Successful, Thankyou !" : "You Are Allready Checked Out !";
    }

    @Override
    public void setEmpStatusForAbsent() {
        attendanceRepository.setEmpStatusForAbsent();
    }

    @Override
    public List<AttendanceResponse> getAllAttendance() {
        List<Attendance> attendanceList = attendanceRepository.getAllAttendance();
        List<AttendanceResponse> attendanceResponseList = new ArrayList<>();
        attendanceList.forEach(attendance -> {
            attendanceResponseList.add(new AttendanceResponse(
                    employeeRepository.getUser(attendance.getEmployeeId()).getUsername(),
                    attendance.getAttendanceDate(),
                    attendance.getCheckInTime() != null ? attendance.getCheckInTime().toLocalTime() : null,
                    attendance.getCheckOutTime() != null ? attendance.getCheckOutTime().toLocalTime() : null,
                    attendance.getWorkingHours(),
                    attendance.getStatus()
            ));
        });
        return attendanceResponseList;
    }

    @Override
    public List<AttendanceResponse> getAllAttendanceByUserName(String userName) {
        List<Attendance> attendanceList = attendanceRepository.getAllAttendanceByUserName(userName);
        List<AttendanceResponse> attendanceResponseList = new ArrayList<>();
        attendanceList.forEach(attendance -> {
            attendanceResponseList.add(new AttendanceResponse(
                    employeeRepository.getUser(attendance.getEmployeeId()).getUsername(),
                    attendance.getAttendanceDate(),
                    attendance.getCheckInTime() != null ? attendance.getCheckInTime().toLocalTime() : null,
                    attendance.getCheckOutTime() != null ? attendance.getCheckOutTime().toLocalTime() : null,
                    attendance.getWorkingHours(),
                    attendance.getStatus()
            ));
        });
        return attendanceResponseList;
    }

    @Override
    public Boolean isInsertedEmployeesToAttendance() {
        return attendanceRepository.isInsertedEmployeesToAttendance();
    }

    @Override
    public void insertEmployeeIdsToAttendance(List<Long> employeeIdList) {
        attendanceRepository.insertEmployeeIdsToAttendance(employeeIdList);
    }

    @Override
    public TodayAttendance getTodayAttDetails(String userName) {
        return attendanceRepository.getTodayAttDetails(userName);
    }
}
