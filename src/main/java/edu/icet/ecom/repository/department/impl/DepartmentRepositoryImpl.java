package edu.icet.ecom.repository.department.impl;

import edu.icet.ecom.entity.Department;
import edu.icet.ecom.repository.department.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DepartmentRepositoryImpl implements DepartmentRepository {

    private final JdbcTemplate template;

    @Override
    public List<Department> getAllDepartments() {
        return template.query("SELECT * FROM departments", new BeanPropertyRowMapper<>(Department.class));
    }

    @Override
    public Department searhDepartmentById(Long departmentId) {
        return template.queryForObject("SELECT * FROM departments WHERE id = ?", new BeanPropertyRowMapper<>(Department.class), departmentId);
    }
}
