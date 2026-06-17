package edu.icet.ecom.repository.employee.impl;

import edu.icet.ecom.entity.Employee;
import edu.icet.ecom.repository.department.DepartmentRepository;
import edu.icet.ecom.repository.employee.EmployeeRepository;
import edu.icet.ecom.repository.mapper.EmployeeRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final JdbcTemplate template;
    private final DepartmentRepository departmentRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return template.query("SELECT * FROM employees", new EmployeeRowMapper(departmentRepository));
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return template.queryForObject("SELECT * FROM employees WHERE id = ?", new EmployeeRowMapper(departmentRepository), id);
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        String sql = "INSERT INTO employees (employee_code, first_name, last_name, email, phone, join_date, department_id)" +
                "VALUES" +
                "(?, ?, ?, ?, ?, ?, ?)";

        template.update(sql,
                employee.getEmployeeCode(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getPhone(),
                LocalDate.now(),
                employee.getDepartment().getId()
        );

        return getEmployeeById(employee.getId());
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        String sql = "UPDATE employees SET first_name = ?, last_name = ?, email = ?, phone = ?, department_id = ? WHERE id  = ?";
        template.update(sql,
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getPhone(),
                employee.getDepartment().getId(),
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
