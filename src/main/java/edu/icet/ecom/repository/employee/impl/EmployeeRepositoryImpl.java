package edu.icet.ecom.repository.employee.impl;

import edu.icet.ecom.model.dto.request.EmployeeCreationRequest;
import edu.icet.ecom.model.dto.response.DepartmentResponse;
import edu.icet.ecom.model.dto.response.EmployeeResponse;
import edu.icet.ecom.model.entity.Employee;
import edu.icet.ecom.repository.department.DepartmentRepository;
import edu.icet.ecom.repository.employee.EmployeeRepository;
import edu.icet.ecom.repository.mapper.EmployeeRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final JdbcTemplate template;
    private final DepartmentRepository departmentRepository;

    @Override
    public List<EmployeeResponse> getAllEmployees() {
        List<Employee> employeeList = template.query("SELECT * FROM employees", new EmployeeRowMapper());
        List<EmployeeResponse> employeeResponseList = new ArrayList<>();
        employeeList.forEach(employee -> {
            employeeResponseList.add(new EmployeeResponse(
                    employee.getEmployeeCode(),
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getEmail(),
                    employee.getPhone(),
                    employee.getJoinDate(),
                    new DepartmentResponse(
                            departmentRepository.searhDepartmentById(employee.getDepartmentId()).getName(),
                            departmentRepository.searhDepartmentById(employee.getDepartmentId()).getDescription()
                    )
            ));
        });
        return employeeResponseList;
    }

    @Override
    public EmployeeResponse getEmployeeById(Long id) {
        Employee employee = template.queryForObject("SELECT * FROM employees WHERE id = ?", new EmployeeRowMapper(), id);
        return new EmployeeResponse(
                employee.getEmployeeCode(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getPhone(),
                employee.getJoinDate(),
                new DepartmentResponse(
                        departmentRepository.searhDepartmentById(employee.getDepartmentId()).getName(),
                        departmentRepository.searhDepartmentById(employee.getDepartmentId()).getDescription()
                )
        );
    }

    @Override
    public EmployeeResponse saveEmployee(EmployeeCreationRequest employeeCreationRequest) {
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
        return getEmployeeById(keyHolder.getKey().longValue());
    }

    @Override
    public EmployeeResponse updateEmployee(Long id, EmployeeCreationRequest employeeCreationRequest) {
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
    public Boolean deleteEmployee(Long id) {
        String sql = "DELETE FROM employees WHERE id = ?";
        int update = template.update(sql, id);
        if (update == 1){
            return true;
        }
        return false;
    }
}
