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

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.getEmployeeById(id);
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.saveEmployee(employee);
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        return employeeRepository.updateEmployee(id, employee);
    }

    @Override
    public Boolean deleteEmployee(Long id) {
        return employeeRepository.deleteEmployee(id);
    }
}
