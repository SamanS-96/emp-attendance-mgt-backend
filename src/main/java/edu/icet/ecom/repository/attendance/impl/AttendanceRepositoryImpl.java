package edu.icet.ecom.repository.attendance.impl;

import edu.icet.ecom.entity.Attendance;
import edu.icet.ecom.model.dto.request.CheckInCreationRequest;
import edu.icet.ecom.model.dto.request.CheckOutCreationRequest;
import edu.icet.ecom.model.dto.response.AttendanceResponse;
import edu.icet.ecom.repository.attendance.AttendanceRepository;
import edu.icet.ecom.repository.employee.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AttendanceRepositoryImpl implements AttendanceRepository {

    private final JdbcTemplate template;
    private final EmployeeRepository employeeRepository;

    @Override
    public Boolean saveCheckIn(CheckInCreationRequest checkInCreationRequest) {
        template.update("INSERT INTO attendance(employee_id, attendance_date, check_in_time, status) VALUES (?, ?, ?, ?)",
                checkInCreationRequest.getEmployeeId(),
                checkInCreationRequest.getCheckInTime().toLocalDate(),
                checkInCreationRequest.getCheckInTime(),
                checkInCreationRequest.getCheckInTime().toLocalTime().isBefore(LocalTime.of(8,30)) ? "PRESENT" : "LATE"
                );
        return true;
    }

    @Override
    public Boolean saveCheckOut(CheckOutCreationRequest checkOutCreationRequest) {
        template.update("UPDATE attendance SET check_out_time = ?, working_hours = ? WHERE employee_id = ? AND attendance_date = CURDATE()",
                checkOutCreationRequest.getCheckOutTime(),
                Duration.between(getAttendanceDetails(checkOutCreationRequest.getEmployeeId()).getCheckInTime().toLocalTime(), checkOutCreationRequest.getCheckOutTime().toLocalTime()).toMinutes()/60.0,
                checkOutCreationRequest.getEmployeeId()
                );
        return true;
    }

    @Override
    public void setEmpStatusForAbsent() {
        template.update("UPDATE attendance SET status = 'ABSENT' WHERE status IS NULL");
    }

    @Override
    public void setStatusForHalfday(Long empId) {
        template.update("UPDATE attendance SET status = 'HALF_DAY' WHERE employee_id = ? AND attendance_date = CURDATE()",
                empId
                );
    }

    @Override
    public List<Attendance> getAllAttendance() {
        return template.query("SELECT * FROM attendance", new BeanPropertyRowMapper<>(Attendance.class));
    }

    @Override
    public List<Attendance> getAllAttendanceByUserName(String userName) {
        return template.query("SELECT * FROM attendance WHERE employee_id = ?", new BeanPropertyRowMapper<>(Attendance.class),
                employeeRepository.getUser(userName).getEmployeeId()
                );
    }

    private Attendance getAttendanceDetails(Long employeeId) {
        return template.queryForObject("SELECT * FROM attendance WHERE employee_id = ? AND attendance_date = CURDATE()",
                new BeanPropertyRowMapper<>(Attendance.class),
                employeeId
                );
    }
}
