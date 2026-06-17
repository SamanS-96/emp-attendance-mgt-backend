package edu.icet.ecom.service.employee;

import edu.icet.ecom.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id);

    Employee saveEmployee(Employee employee);

    Employee updateEmployee(Long id, Employee employee);

    Boolean deleteEmployee(Long id);
}
