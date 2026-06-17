package edu.icet.ecom.repository.employee;

import edu.icet.ecom.model.dto.request.EmployeeCreationRequest;
import edu.icet.ecom.model.dto.response.EmployeeResponse;
import edu.icet.ecom.model.entity.Employee;

import java.util.List;

public interface EmployeeRepository {

    List<EmployeeResponse> getAllEmployees();

    EmployeeResponse getEmployeeById(Long id);

    EmployeeResponse saveEmployee(EmployeeCreationRequest employeeCreationRequest);

    EmployeeResponse updateEmployee(Long id, EmployeeCreationRequest employeeCreationRequest);

    Boolean deleteEmployee(Long id);

}
