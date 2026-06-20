package edu.icet.ecom.repository.attendance;

import edu.icet.ecom.entity.Attendance;
import edu.icet.ecom.model.dto.request.CheckInCreationRequest;
import edu.icet.ecom.model.dto.request.CheckOutCreationRequest;
import edu.icet.ecom.model.dto.response.AttendanceResponse;

import java.util.List;

public interface AttendanceRepository {
    Boolean saveCheckIn(CheckInCreationRequest checkInCreationRequest);

    Boolean saveCheckOut(CheckOutCreationRequest checkOutCreationRequest);

    void setEmpStatusForAbsent();

    void setStatusForHalfday(Long empId);

    List<Attendance> getAllAttendance();
}
