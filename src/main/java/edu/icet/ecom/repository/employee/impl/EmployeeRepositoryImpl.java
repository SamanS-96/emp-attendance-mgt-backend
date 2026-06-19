package edu.icet.ecom.repository.employee.impl;

import edu.icet.ecom.model.dto.request.EmployeeCreationRequest;
import edu.icet.ecom.entity.Employee;
import edu.icet.ecom.entity.User;
import edu.icet.ecom.repository.employee.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final JdbcTemplate template;

    @Override
    public List<Employee> getAllEmployees() {
        return template.query("SELECT * FROM employees WHERE is_active = TRUE", new BeanPropertyRowMapper<>(Employee.class));
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return template.queryForObject("SELECT * FROM employees WHERE id = ?", new BeanPropertyRowMapper<>(Employee.class), id);
    }

    Long generatedEmpId = null;

    @Override
    public Employee saveEmployee(EmployeeCreationRequest employeeCreationRequest) {
        String sql = "INSERT INTO employees (employee_code, first_name, last_name, email, phone, join_date, department_id)" +
                "VALUES" +
                "(?, ?, ?, ?, ?, ?, ?)";

        String lastEmpCode = template.queryForObject("SELECT MAX(employee_code) FROM employees", String.class);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        template.update(connection ->{
            PreparedStatement psTm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            psTm.setString(1,lastEmpCode == null ? "EMP001" : String.format("EMP%03d", Integer.parseInt(lastEmpCode.substring(3)) + 1));
            psTm.setString(2,employeeCreationRequest.getFirstName());
            psTm.setString(3,employeeCreationRequest.getLastName());
            psTm.setString(4,employeeCreationRequest.getEmail());
            psTm.setString(5,employeeCreationRequest.getPhone());
            psTm.setObject(6,LocalDate.now());
            psTm.setLong(7,employeeCreationRequest.getDepartmentId());
            return psTm;
        }, keyHolder);
        generatedEmpId = keyHolder.getKey().longValue();
        return getEmployeeById(generatedEmpId);
    }

    @Override
    public Employee updateEmployee(Long id, EmployeeCreationRequest employeeCreationRequest) {
        String sql = "UPDATE employees SET first_name = ?, last_name = ?, email = ?, phone = ?, department_id = ? WHERE id  = ?";

        template.update(sql,
                employeeCreationRequest.getFirstName(),
                employeeCreationRequest.getLastName(),
                employeeCreationRequest.getEmail(),
                employeeCreationRequest.getPhone(),
                employeeCreationRequest.getDepartmentId(),
                id
        );
        return getEmployeeById(id);
    }

    @Override
    public Boolean deactivateEmployee(Long id) {
        String sql = "UPDATE employees SET is_active = FALSE WHERE id = ?";
        int update = template.update(sql, id);
        if (update == 1){
            return true;
        }
        return false;
    }

    @Override
    public User getUser(Long id) {
        return template.queryForObject("SELECT * FROM users WHERE employee_id = ?", new BeanPropertyRowMapper<>(User.class), id);
    }

    @Override
    public User createNewUser(EmployeeCreationRequest employeeCreationRequest) {

        String sql = "INSERT INTO users(username, password, role, employee_id) VALUES (?, ?, ?, ?)";
        String lastEmpCode = template.queryForObject("SELECT MAX(employee_code) FROM employees", String.class);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        template.update(connection ->{
            PreparedStatement psTm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            psTm.setString(1,employeeCreationRequest.getFirstName()+"_"+lastEmpCode);
            psTm.setString(2,employeeCreationRequest.getFirstName()+"@123");
            psTm.setString(3,employeeCreationRequest.getRole());
            psTm.setLong(4,generatedEmpId);
            return psTm;
        }, keyHolder);
        return getUser(generatedEmpId);
    }

    @Override
    public User updateUser(Long id, EmployeeCreationRequest employeeCreationRequest) {
        String sql = "UPDATE users SET username = ?, password = ?, role = ? WHERE employee_id = ?";
        template.update(sql,
                employeeCreationRequest.getFirstName()+"_"+getEmployeeById(id).getEmployeeCode(),
                employeeCreationRequest.getFirstName()+"@123",
                employeeCreationRequest.getRole(),
                id
                );
        return getUser(id);
    }
}
