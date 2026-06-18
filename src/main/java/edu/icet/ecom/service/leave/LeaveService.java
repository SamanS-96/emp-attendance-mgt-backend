package edu.icet.ecom.service.leave;

import edu.icet.ecom.model.dto.request.LeaveRequest;

public interface LeaveService {
    Boolean createLeave(LeaveRequest leaveRequest);
}
