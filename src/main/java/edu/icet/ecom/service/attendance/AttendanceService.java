package edu.icet.ecom.service.attendance;

import edu.icet.ecom.model.dto.request.CheckInCreationRequest;
import edu.icet.ecom.model.dto.request.CheckOutCreationRequest;
import edu.icet.ecom.model.dto.response.AttendanceResponse;

import java.util.List;

public interface AttendanceService {
    Boolean saveCheckIn(CheckInCreationRequest checkInCreationRequest);

    Boolean saveCheckOut(CheckOutCreationRequest checkOutCreationRequest);

    void setEmpStatusForAbsent();

    List<AttendanceResponse> getAllAttendance();
}
