package edu.icet.ecom.repository.leave;

import edu.icet.ecom.entity.Leave;
import edu.icet.ecom.model.dto.request.LeaveRequest;

import java.util.List;

public interface LeaveRepository {
    Boolean createLeave(LeaveRequest leaveRequest);

    Boolean updateLeave(Long id, LeaveRequest leaveRequest);

    List<Leave> getAll();

    Leave getById(Long id);

    List<Leave> getAllLeavesByUserName(String userName);

    boolean isOnLeaveToday(Long employeeId);

    Boolean approveLeaveReq(Long id);

    Boolean rejectLeaveReq(Long id);
}
