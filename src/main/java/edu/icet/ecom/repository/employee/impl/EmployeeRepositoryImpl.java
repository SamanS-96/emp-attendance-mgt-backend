package edu.icet.ecom.repository.employee.impl;

import edu.icet.ecom.entity.Employee;
import edu.icet.ecom.repository.department.DepartmentRepository;
import edu.icet.ecom.repository.employee.EmployeeRepository;
import edu.icet.ecom.repository.mapper.EmployeeRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
}
