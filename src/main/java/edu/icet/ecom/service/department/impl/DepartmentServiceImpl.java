package edu.icet.ecom.service.department.impl;

import edu.icet.ecom.model.dto.request.DepartmentCreationRequest;
import edu.icet.ecom.model.dto.response.DepartmentResponse;
import edu.icet.ecom.model.entity.Department;
import edu.icet.ecom.repository.department.DepartmentRepository;
import edu.icet.ecom.service.department.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public List<DepartmentResponse> getAllDepartments() {
        return departmentRepository.getAllDepartments();
    }

    @Override
    public DepartmentResponse getDepartmentById(Long id) {
        return departmentRepository.searhDepartmentById(id);
    }

    @Override
    public DepartmentResponse saveDepartment(DepartmentCreationRequest departmentCreationRequest) {
        return departmentRepository.saveDepartment(departmentCreationRequest);
    }

    @Override
    public DepartmentResponse updateDepartment(Long id, DepartmentCreationRequest departmentCreationRequest) {
        return departmentRepository.updateDepartment(id, departmentCreationRequest);
    }

    @Override
    public Boolean deleteDepartment(Long id) {
        return departmentRepository.deleteDepartment(id);
    }
}
