package edu.icet.ecom.service.employee.impl;

import edu.icet.ecom.entity.Employee;
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
    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }
}
