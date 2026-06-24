package edu.icet.ecom.service.department.impl;

import edu.icet.ecom.model.dto.request.DepartmentCreationRequest;
import edu.icet.ecom.model.dto.response.DepartmentResponse;
import edu.icet.ecom.entity.Department;
import edu.icet.ecom.repository.department.DepartmentRepository;
import edu.icet.ecom.service.department.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public List<DepartmentResponse> getAllDepartments() {
        List<Department> allDepartments = departmentRepository.getAllDepartments();
        List<DepartmentResponse> departmentResponseList = new ArrayList<>();
        allDepartments.forEach(department -> {
            departmentResponseList.add(new DepartmentResponse(
                    department.getId(),
                    department.getName(),
                    department.getDescription()
            ));
        });
        return departmentResponseList;
    }

    @Override
    public DepartmentResponse getDepartmentById(Long id) {
        Department department = departmentRepository.searhDepartmentById(id);
        return new DepartmentResponse(
                department.getId(),
                department.getName(),
                department.getDescription()
        );
    }

    @Override
    public DepartmentResponse saveDepartment(DepartmentCreationRequest departmentCreationRequest) {
        Department department = departmentRepository.saveDepartment(departmentCreationRequest);
        if (department == null){
            return null;
        }

        return new DepartmentResponse(
                department.getId(),
                department.getName(),
                department.getDescription()
        );
    }

    @Override
    public DepartmentResponse updateDepartment(Long id, DepartmentCreationRequest departmentCreationRequest) {
        Department department = departmentRepository.updateDepartment(id, departmentCreationRequest);
        if (department == null){
            return null;
        }

        return new DepartmentResponse(
                department.getId(),
                department.getName(),
                department.getDescription()
        );
    }

    @Override
    public List<String> getDepartmentNames() {
        List<DepartmentResponse> allDepartments = getAllDepartments();
        List<String> departmentNamesList = new ArrayList<>();
        allDepartments.forEach(departmentResponse -> {
            departmentNamesList.add(departmentResponse.getName());
        });
        return departmentNamesList;
    }

}
