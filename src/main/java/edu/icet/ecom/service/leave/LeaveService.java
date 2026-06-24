package edu.icet.ecom.service.leave;

import edu.icet.ecom.model.dto.request.LeaveRequest;
import edu.icet.ecom.model.dto.response.AttendanceResponse;
import edu.icet.ecom.model.dto.response.LeaveResponse;

import java.util.List;

public interface LeaveService {
    Boolean createLeave(LeaveRequest leaveRequest);

    Boolean updateLeave(Long id, LeaveRequest leaveRequest);

    List<LeaveResponse> geAll();

    LeaveResponse geById(Long id);

    List<LeaveResponse> getAllLeavesByUserName(String userName);

    Boolean approveLeaveReq(Long id);

    Boolean rejectLeaveReq(Long id);
}
