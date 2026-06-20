package edu.icet.ecom.service.leave.impl;

import edu.icet.ecom.entity.Leave;
import edu.icet.ecom.model.dto.request.LeaveRequest;
import edu.icet.ecom.model.dto.response.LeaveResponse;
import edu.icet.ecom.repository.employee.EmployeeRepository;
import edu.icet.ecom.repository.leave.LeaveRepository;
import edu.icet.ecom.service.leave.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaveServiceImpl implements LeaveService {

    private final LeaveRepository leaveRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public Boolean createLeave(LeaveRequest leaveRequest) {
        return leaveRepository.createLeave(leaveRequest);
    }

    @Override
    public Boolean updateLeave(Long id, LeaveRequest leaveRequest) {
        return leaveRepository.updateLeave(id, leaveRequest);
    }

    @Override
    public List<LeaveResponse> geAll() {
        List<Leave> leaveList = leaveRepository.getAll();
        List<LeaveResponse> leaveResponseList = new ArrayList<>();
        leaveList.forEach(leave -> {
            leaveResponseList.add(new LeaveResponse(
                    leave.getId(),
                    employeeRepository.getUser(leave.getEmployeeId()).getUsername(),
                    leave.getFromDate(),
                    leave.getToDate(),
                    leave.getReason(),
                    leave.getStatus()
            ));
        });
        return leaveResponseList;
    }

    @Override
    public LeaveResponse geById(Long id) {
        Leave leave = leaveRepository.getById(id);
        return new LeaveResponse(
                leave.getId(),
                employeeRepository.getUser(leave.getEmployeeId()).getUsername(),
                leave.getFromDate(),
                leave.getToDate(),
                leave.getReason(),
                leave.getStatus()
        );
    }
}
