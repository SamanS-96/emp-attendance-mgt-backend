package edu.icet.ecom.repository.department.impl;

import edu.icet.ecom.model.dto.request.DepartmentCreationRequest;
import edu.icet.ecom.model.dto.response.DepartmentResponse;
import edu.icet.ecom.model.entity.Department;
import edu.icet.ecom.repository.department.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
public class DepartmentRepositoryImpl implements DepartmentRepository {

    private final JdbcTemplate template;

    @Override
    public List<DepartmentResponse> getAllDepartments() {
        List<Department> departmentList = template.query("SELECT * FROM departments", new BeanPropertyRowMapper<>(Department.class));
        List<DepartmentResponse> departmentResponseList = new ArrayList<>();
        departmentList.forEach(department -> {
            departmentResponseList.add(new DepartmentResponse(
                    department.getName(),
                    department.getDescription()));
        });
        return departmentResponseList;
    }

    @Override
    public DepartmentResponse searhDepartmentById(Long departmentId) {
        Department department = template.queryForObject("SELECT * FROM departments WHERE id = ?", new BeanPropertyRowMapper<>(Department.class), departmentId);
        return new DepartmentResponse(
                department.getName(),
                department.getDescription());
    }

    @Override
    public DepartmentResponse saveDepartment(DepartmentCreationRequest departmentCreationRequest) {
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
    public DepartmentResponse updateDepartment(Long id, DepartmentCreationRequest departmentCreationRequest) {
        String sql = "UPDATE departments SET name = ?, description = ? WHERE id = ?";

        template.update(sql,
                departmentCreationRequest.getName(),
                departmentCreationRequest.getDescription(),
                id
        );
        return searhDepartmentById(id);
    }

    @Override
    public Boolean deleteDepartment(Long id) {
        String sql = "DELETE FROM departments WHERE id = ?";
        int update = template.update(sql, id);
        if (update == 1){
            return true;
        }
        return false;
    }
}
