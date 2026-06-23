package edu.icet.ecom.repository.leave.impl;

import edu.icet.ecom.entity.Leave;
import edu.icet.ecom.model.dto.request.LeaveRequest;
import edu.icet.ecom.repository.employee.EmployeeRepository;
import edu.icet.ecom.repository.employee.impl.EmployeeRepositoryImpl;
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
    private final EmployeeRepository employeeRepository;

    @Override
    public Boolean createLeave(LeaveRequest leaveRequest) {

        template.update("INSERT INTO leave_requests(employee_id, from_date, to_date, reason) VALUES (?, ?, ?, ?)",
                employeeRepository.getUser(leaveRequest.getUserName()).getEmployeeId(),
                leaveRequest.getFromDate(),
                leaveRequest.getToDate(),
                leaveRequest.getReason()
                );
        return true;
    }

    @Override
    public Boolean updateLeave(Long id, LeaveRequest leaveRequest) {
        template.update("UPDATE leave_requests SET employee_id = ?, from_date = ?, to_date = ?, reason = ? WHERE id = ?",
                employeeRepository.getUser(leaveRequest.getUserName()).getEmployeeId(),
                leaveRequest.getFromDate(),
                leaveRequest.getToDate(),
                leaveRequest.getReason(),
                id
                );
        return true;
    }

    @Override
    public List<Leave> getAll() {
        return template.query("SELECT*FROM leave_requests ORDER BY id DESC", new BeanPropertyRowMapper<>(Leave.class));
    }

    @Override
    public Leave getById(Long id) {
        return template.queryForObject("SELECT*FROM leave_requests WHERE id = ?", new BeanPropertyRowMapper<>(Leave.class), id);
    }

    @Override
    public List<Leave> getAllLeavesByUserName(String userName) {
        return template.query("SELECT*FROM leave_requests WHERE employee_id = ? ORDER BY id DESC", new BeanPropertyRowMapper<>(Leave.class),
                employeeRepository.getUser(userName).getEmployeeId()
                );
    }

    @Override
    public boolean isOnLeaveToday(Long employeeId) {
        Integer count = template.queryForObject(
                "SELECT COUNT(*) FROM leave_requests WHERE employee_id = ? AND status = 'APPROVED' AND CURDATE() BETWEEN from_date AND to_date",
                Integer.class,
                employeeId
        );
        if (count != null && count > 0){
            return true;
        }
        return false;
    }

    @Override
    public Boolean approveLeaveReq(Long id) {
        template.update("UPDATE leave_requests SET status = 'APPROVED' WHERE id = ?", id);
        return true;
    }

    @Override
    public Boolean rejectLeaveReq(Long id) {
        template.update("UPDATE leave_requests SET status = 'REJECTED' WHERE id = ?", id);
        return true;
    }
}
