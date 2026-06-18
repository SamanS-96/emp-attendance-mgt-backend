package edu.icet.ecom.repository.department;

import edu.icet.ecom.model.dto.request.DepartmentCreationRequest;
import edu.icet.ecom.entity.Department;

import java.util.List;

public interface DepartmentRepository {
    List<Department> getAllDepartments();

    Department searhDepartmentById(Long departmentId);

    Department saveDepartment(DepartmentCreationRequest departmentCreationRequest);

    Department updateDepartment(Long id, DepartmentCreationRequest departmentCreationRequest);

}
