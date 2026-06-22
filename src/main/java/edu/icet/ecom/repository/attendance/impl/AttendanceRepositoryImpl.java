package edu.icet.ecom.repository.attendance.impl;

import edu.icet.ecom.entity.Attendance;
import edu.icet.ecom.model.dto.request.CheckInCreationRequest;
import edu.icet.ecom.model.dto.request.CheckOutCreationRequest;
import edu.icet.ecom.model.dto.response.TodayAttendance;
import edu.icet.ecom.repository.attendance.AttendanceRepository;
import edu.icet.ecom.repository.employee.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AttendanceRepositoryImpl implements AttendanceRepository {

    private final JdbcTemplate template;
    private final EmployeeRepository employeeRepository;

    @Override
    public Boolean saveCheckIn(CheckInCreationRequest checkInCreationRequest) {

        Integer count = template.queryForObject("SELECT COUNT(*) FROM attendance WHERE employee_id = ? AND check_in_time IS NULL AND attendance_date = CURDATE()", Integer.class, employeeRepository.getUser(checkInCreationRequest.getUserName()).getEmployeeId());
        if (count == 1) {
            template.update("UPDATE attendance SET check_in_time = ?, status = ? WHERE employee_id = ? AND attendance_date = CURDATE()",
                    LocalDateTime.now(),
                    LocalTime.now().isBefore(LocalTime.of(8,30)) ? "PRESENT" : "LATE",
                    employeeRepository.getUser(checkInCreationRequest.getUserName()).getEmployeeId()
            );
            return true;
        }
        return false;
    }

    @Override
    public Boolean saveCheckOut(CheckOutCreationRequest checkOutCreationRequest) {

        Integer count = template.queryForObject("SELECT COUNT(*) FROM attendance WHERE employee_id = ? AND check_out_time IS NULL AND attendance_date = CURDATE()", Integer.class, employeeRepository.getUser(checkOutCreationRequest.getUserName()).getEmployeeId());
        if (count == 1) {
            template.update("UPDATE attendance SET check_out_time = ?, working_hours = ? WHERE employee_id = ? AND attendance_date = CURDATE()",
                    LocalDateTime.now(),
                    Duration.between(getAttendanceDetails(employeeRepository.getUser(checkOutCreationRequest.getUserName()).getEmployeeId()).getCheckInTime().toLocalTime(), LocalTime.now()).toMinutes()/60.0,
                    employeeRepository.getUser(checkOutCreationRequest.getUserName()).getEmployeeId()
            );
            return true;
        }
        return false;
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

    @Override
    public Boolean isInsertedEmployeesToAttendance() {
        String sql = "SELECT COUNT(*) FROM attendance WHERE attendance_date = CURDATE()";
        Integer count = template.queryForObject(sql, Integer.class);

        if (count != null && count > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void insertEmployeeIdsToAttendance(List<Long> employeeIdList) {
        String sql = "INSERT INTO attendance (employee_id, attendance_date) VALUES (?, CURDATE())";

        for (Long employeeId : employeeIdList) {
            template.update(sql, employeeId);
        }
    }

    @Override
    public Boolean isCheckedIn(String userName) {
        String sql = "SELECT COUNT(*) FROM attendance WHERE employee_id = ? AND check_in_time IS NULL AND attendance_date = CURDATE()";
        Integer count = template.queryForObject(sql, Integer.class, employeeRepository.getUser(userName).getEmployeeId());

        if (count != 1) {
            return true;
        }
        return false;
    }

    @Override
    public TodayAttendance getTodayAttDetails(String userName) {
        Integer presentCount = template.queryForObject("SELECT COUNT(*) FROM attendance WHERE status = 'PRESENT' OR status = 'HALF_DAY'", Integer.class);
        Integer absentCount = template.queryForObject("SELECT COUNT(*) FROM attendance WHERE status = 'ABSENT'", Integer.class);
        String myStatus = template.queryForObject("SELECT status  FROM attendance WHERE employee_id = ? AND attendance_date = CURDATE()",
                String.class,
                employeeRepository.getUser(userName).getEmployeeId()
                );
        return new TodayAttendance(presentCount, absentCount, myStatus);
    }

    private Attendance getAttendanceDetails(Long employeeId) {
        return template.queryForObject("SELECT * FROM attendance WHERE employee_id = ? AND attendance_date = CURDATE()",
                new BeanPropertyRowMapper<>(Attendance.class),
                employeeId
                );
    }
}
