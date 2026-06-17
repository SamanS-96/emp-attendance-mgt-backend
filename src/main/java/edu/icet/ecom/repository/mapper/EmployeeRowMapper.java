package edu.icet.ecom.repository.mapper;

import edu.icet.ecom.entity.Department;
import edu.icet.ecom.entity.Employee;
import edu.icet.ecom.repository.department.DepartmentRepository;
import edu.icet.ecom.repository.department.impl.DepartmentRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@RequiredArgsConstructor
public class EmployeeRowMapper implements RowMapper<Employee> {

    private final DepartmentRepository departmentRepository;

    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee employee = new Employee();
        employee.setId(rs.getLong("id"));
        employee.setEmployeeCode(rs.getString("employee_code"));
        employee.setFirstName(rs.getString("first_name"));
        employee.setLastName(rs.getString("last_name"));
        employee.setEmail(rs.getString("email"));
        employee.setPhone(rs.getString("phone"));
        employee.setJoinDate(rs.getDate("join_date").toLocalDate());
        employee.setIsActive(rs.getBoolean("is_active"));

        Department department = departmentRepository.searhDepartmentById(rs.getLong("department_id"));
        employee.setDepartment(department);

        return employee;
    }
}
