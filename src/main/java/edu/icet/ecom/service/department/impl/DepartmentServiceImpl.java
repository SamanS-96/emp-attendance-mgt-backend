package edu.icet.ecom.service.department.impl;

import edu.icet.ecom.entity.Department;
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
    public List<Department> getAllDepartments() {
        return departmentRepository.getAllDepartments();
    }
}
