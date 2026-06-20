package edu.icet.ecom.service.employee;

import edu.icet.ecom.model.dto.request.EmployeeCreationRequest;
import edu.icet.ecom.model.dto.request.LoginRequest;
import edu.icet.ecom.model.dto.response.EmployeeResponse;
import edu.icet.ecom.model.dto.response.UserResponse;

import java.util.List;

public interface EmployeeService {
    List<EmployeeResponse> getAllEmployees();

    EmployeeResponse getEmployeeById(Long id);

    EmployeeResponse saveEmployee(EmployeeCreationRequest employeeCreationRequest);

    EmployeeResponse updateEmployee(Long id, EmployeeCreationRequest employeeCreationRequest);

    Boolean deactivateEmployee(Long id);

    UserResponse getUserDetails(LoginRequest loginRequest);
}
