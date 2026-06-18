package edu.icet.ecom.service.leave.impl;

import edu.icet.ecom.model.dto.request.LeaveRequest;
import edu.icet.ecom.repository.leave.LeaveRepository;
import edu.icet.ecom.service.leave.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LeaveServiceImpl implements LeaveService {

    private final LeaveRepository leaveRepository;

    @Override
    public Boolean createLeave(LeaveRequest leaveRequest) {
        return leaveRepository.createLeave(leaveRequest);
    }
}
