package edu.icet.ecom.repository.department;

import edu.icet.ecom.model.dto.request.DepartmentCreationRequest;
import edu.icet.ecom.model.dto.response.DepartmentResponse;
import edu.icet.ecom.model.entity.Department;

import java.util.List;

public interface DepartmentRepository {
    List<DepartmentResponse> getAllDepartments();

    DepartmentResponse searhDepartmentById(Long departmentId);

    DepartmentResponse saveDepartment(DepartmentCreationRequest departmentCreationRequest);

    DepartmentResponse updateDepartment(Long id, DepartmentCreationRequest departmentCreationRequest);

    Boolean deleteDepartment(Long id);
}
