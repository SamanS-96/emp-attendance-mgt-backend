package edu.icet.ecom.repository.leave;

import edu.icet.ecom.entity.Leave;
import edu.icet.ecom.model.dto.request.LeaveRequest;

import java.util.List;

public interface LeaveRepository {
    Boolean createLeave(LeaveRequest leaveRequest);

    Boolean uddateLeave(Long id, LeaveRequest leaveRequest);

    List<Leave> getAll();
}
