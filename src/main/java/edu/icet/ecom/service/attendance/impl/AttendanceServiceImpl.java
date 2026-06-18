package edu.icet.ecom.service.attendance.impl;

import edu.icet.ecom.model.dto.request.CheckInCreationRequest;
import edu.icet.ecom.model.dto.request.CheckOutCreationRequest;
import edu.icet.ecom.repository.attendance.AttendanceRepository;
import edu.icet.ecom.service.attendance.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;

    @Override
    public Boolean saveCheckIn(CheckInCreationRequest checkInCreationRequest) {
        return attendanceRepository.saveCheckIn(checkInCreationRequest);
    }

    @Override
    public Boolean saveCheckOut(CheckOutCreationRequest checkOutCreationRequest) {
        return attendanceRepository.saveCheckOut(checkOutCreationRequest);
    }
}
