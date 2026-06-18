package edu.icet.ecom.repository.leave;

import edu.icet.ecom.model.dto.request.LeaveRequest;

public interface LeaveRepository {
    Boolean createLeave(LeaveRequest leaveRequest);
}
