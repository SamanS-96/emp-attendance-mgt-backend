package edu.icet.ecom.service.employee.impl;

import edu.icet.ecom.model.dto.request.EmployeeCreationRequest;
import edu.icet.ecom.model.dto.response.EmployeeResponse;
import edu.icet.ecom.model.entity.Employee;
import edu.icet.ecom.repository.employee.EmployeeRepository;
import edu.icet.ecom.service.employee.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeResponse> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    @Override
    public EmployeeResponse getEmployeeById(Long id) {
        return employeeRepository.getEmployeeById(id);
    }

    @Override
    public EmployeeResponse saveEmployee(EmployeeCreationRequest employeeCreationRequest) {
        return employeeRepository.saveEmployee(employeeCreationRequest);
    }

    @Override
    public EmployeeResponse updateEmployee(Long id, EmployeeCreationRequest employeeCreationRequest) {
        return employeeRepository.updateEmployee(id, employeeCreationRequest);
    }

    @Override
    public Boolean deleteEmployee(Long id) {
        return employeeRepository.deleteEmployee(id);
    }
}
