package edu.icet.ecom.service.attendance.impl;

import edu.icet.ecom.entity.Attendance;
import edu.icet.ecom.model.dto.request.CheckInCreationRequest;
import edu.icet.ecom.model.dto.request.CheckOutCreationRequest;
import edu.icet.ecom.model.dto.response.AttendanceResponse;
import edu.icet.ecom.model.dto.response.EmployeeResponse;
import edu.icet.ecom.repository.attendance.AttendanceRepository;
import edu.icet.ecom.repository.employee.EmployeeRepository;
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

    @Override
    @Transactional
    public Boolean saveCheckIn(CheckInCreationRequest checkInCreationRequest) {

        if (checkInCreationRequest.getCheckInTime().toLocalTime().isAfter(LocalTime.of(12,30))){
            attendanceRepository.saveCheckIn(checkInCreationRequest);
            attendanceRepository.setStatusForHalfday(checkInCreationRequest.getEmployeeId());
            return true;
        }
        return attendanceRepository.saveCheckIn(checkInCreationRequest);
    }

    @Override
    @Transactional
    public Boolean saveCheckOut(CheckOutCreationRequest checkOutCreationRequest) {

        if (checkOutCreationRequest.getCheckOutTime().toLocalTime().isBefore(LocalTime.of(13,30))){
            attendanceRepository.saveCheckOut(checkOutCreationRequest);
            attendanceRepository.setStatusForHalfday(checkOutCreationRequest.getEmployeeId());
            return true;
        }
        return attendanceRepository.saveCheckOut(checkOutCreationRequest);
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
}
