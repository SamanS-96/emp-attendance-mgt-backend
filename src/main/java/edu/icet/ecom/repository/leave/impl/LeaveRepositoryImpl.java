package edu.icet.ecom.repository.leave.impl;

import edu.icet.ecom.entity.Leave;
import edu.icet.ecom.model.dto.request.LeaveRequest;
import edu.icet.ecom.repository.leave.LeaveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class LeaveRepositoryImpl implements LeaveRepository {

    private final JdbcTemplate template;

    @Override
    public Boolean createLeave(LeaveRequest leaveRequest) {

        template.update("INSERT INTO leave_requests(employee_id, from_date, to_date, reason) VALUES (?, ?, ?, ?)",
                leaveRequest.getEmployeeId(),
                leaveRequest.getFromDate(),
                leaveRequest.getToDate(),
                leaveRequest.getReason()
                );
        return true;
    }

    @Override
    public Boolean uddateLeave(Long id, LeaveRequest leaveRequest) {
        template.update("UPDATE leave_requests SET employee_id = ?, from_date = ?, to_date = ?, reason + ? WHERE id = ?",
                leaveRequest.getEmployeeId(),
                leaveRequest.getFromDate(),
                leaveRequest.getToDate(),
                leaveRequest.getReason(),
                id
                );
        return true;
    }

    @Override
    public List<Leave> getAll() {
        return template.query("SELECT*FROM leave_requests", new BeanPropertyRowMapper<>(Leave.class));
    }
}
