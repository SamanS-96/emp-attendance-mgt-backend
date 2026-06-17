package edu.icet.ecom.repository.employee;

import edu.icet.ecom.entity.Employee;

import java.util.List;

public interface EmployeeRepository {
    List<Employee> getAllEmployees();
}
