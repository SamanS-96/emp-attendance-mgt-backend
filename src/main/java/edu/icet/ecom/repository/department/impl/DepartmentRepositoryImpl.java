package edu.icet.ecom.repository.department.impl;

import edu.icet.ecom.model.dto.request.DepartmentCreationRequest;
import edu.icet.ecom.entity.Department;
import edu.icet.ecom.repository.department.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
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

    @Override
    public Department saveDepartment(DepartmentCreationRequest departmentCreationRequest) {
        String sql = "INSERT INTO departments (name, description)" +
                "VALUES" +
                "(?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        template.update(connection ->{
            PreparedStatement psTm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            psTm.setString(1,departmentCreationRequest.getName());
            psTm.setString(2,departmentCreationRequest.getDescription());
            return psTm;
        }, keyHolder);
        return searhDepartmentById(keyHolder.getKey().longValue());
    }

    @Override
    public Department updateDepartment(Long id, DepartmentCreationRequest departmentCreationRequest) {
        String sql = "UPDATE departments SET name = ?, description = ? WHERE id = ?";

        template.update(sql,
                departmentCreationRequest.getName(),
                departmentCreationRequest.getDescription(),
                id
        );
        return searhDepartmentById(id);
    }

}
