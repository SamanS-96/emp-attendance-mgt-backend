package edu.icet.ecom.repository.employee;

import edu.icet.ecom.model.dto.request.EmployeeCreationRequest;
import edu.icet.ecom.entity.Employee;
import edu.icet.ecom.entity.User;

import java.util.List;

public interface EmployeeRepository {

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id);

    Employee saveEmployee(EmployeeCreationRequest employeeCreationRequest);

    Employee updateEmployee(Long id, EmployeeCreationRequest employeeCreationRequest);

    Boolean deactivateEmployee(Long id);

    User getUser(Long id);

    User getUser(String userName);

    User createNewUser(EmployeeCreationRequest employeeCreationRequest);

    User updateUser(Long id, EmployeeCreationRequest employeeCreationRequest);
}
