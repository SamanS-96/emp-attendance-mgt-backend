package edu.icet.ecom.service.attendance;

import edu.icet.ecom.model.dto.request.CheckInCreationRequest;
import edu.icet.ecom.model.dto.request.CheckOutCreationRequest;
import edu.icet.ecom.model.dto.response.AttendanceResponse;
import edu.icet.ecom.model.dto.response.EmployeeResponse;
import edu.icet.ecom.model.dto.response.TodayAttendance;

import java.util.List;

public interface AttendanceService {
    String saveCheckIn(CheckInCreationRequest checkInCreationRequest);

    String saveCheckOut(CheckOutCreationRequest checkOutCreationRequest);

    void setEmpStatusForAbsent();

    List<AttendanceResponse> getAllAttendance();

    List<AttendanceResponse> getAllAttendanceByUserName(String userName);

    Boolean isInsertedEmployeesToAttendance();

    void insertEmployeeIdsToAttendance(List<Long> employeeIdList);

    TodayAttendance getTodayAttDetails(String userName);
}
