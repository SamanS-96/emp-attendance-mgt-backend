package edu.icet.ecom.service.department;

import edu.icet.ecom.model.dto.request.DepartmentCreationRequest;
import edu.icet.ecom.model.dto.response.DepartmentResponse;

import java.util.List;

public interface DepartmentService {
    List<DepartmentResponse> getAllDepartments();

    DepartmentResponse getDepartmentById(Long id);

    DepartmentResponse saveDepartment(DepartmentCreationRequest departmentCreationRequest);

    DepartmentResponse updateDepartment(Long id, DepartmentCreationRequest departmentCreationRequest);

    List<String> getDepartmentNames();
}
