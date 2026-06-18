package edu.icet.ecom.service.attendance.impl;

import edu.icet.ecom.model.dto.request.CheckInCreationRequest;
import edu.icet.ecom.model.dto.request.CheckOutCreationRequest;
import edu.icet.ecom.repository.attendance.AttendanceRepository;
import edu.icet.ecom.service.attendance.AttendanceService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;

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
}
