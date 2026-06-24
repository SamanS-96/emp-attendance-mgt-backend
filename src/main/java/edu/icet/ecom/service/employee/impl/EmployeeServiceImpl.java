package edu.icet.ecom.service.employee.impl;

import edu.icet.ecom.entity.Employee;
import edu.icet.ecom.entity.User;
import edu.icet.ecom.model.dto.request.EmployeeCreationRequest;
import edu.icet.ecom.model.dto.request.LoginRequest;
import edu.icet.ecom.model.dto.response.DepartmentResponse;
import edu.icet.ecom.model.dto.response.EmployeeResponse;
import edu.icet.ecom.model.dto.response.UserResponse;
import edu.icet.ecom.repository.department.DepartmentRepository;
import edu.icet.ecom.repository.employee.EmployeeRepository;
import edu.icet.ecom.service.employee.EmployeeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public List<EmployeeResponse> getAllEmployees() {
        List<Employee> allEmployees = employeeRepository.getAllEmployees();
        List<EmployeeResponse> employeeResponseList = new ArrayList<>();
        allEmployees.forEach(employee -> {
            employeeResponseList.add(new EmployeeResponse(
                    employee.getId(),
                    employee.getEmployeeCode(),
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getEmail(),
                    employee.getPhone(),
                    employee.getJoinDate(),
                    new DepartmentResponse(
                            employee.getDepartmentId(),
                            departmentRepository.searhDepartmentById(employee.getDepartmentId()).getName(),
                            departmentRepository.searhDepartmentById(employee.getDepartmentId()).getDescription()
                    ),
                    employeeRepository.getUser(employee.getId()).getRole(),
                    employeeRepository.getUser(employee.getId()).getUsername(),
                    employeeRepository.getUser(employee.getId()).getPassword()
            ));
        });
        return employeeResponseList;
    }

    @Override
    public EmployeeResponse getEmployeeById(Long id) {
        Employee employee = employeeRepository.getEmployeeById(id);
        return new EmployeeResponse(
                employee.getId(),
                employee.getEmployeeCode(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getPhone(),
                employee.getJoinDate(),
                new DepartmentResponse(
                        employee.getDepartmentId(),
                        departmentRepository.searhDepartmentById(employee.getDepartmentId()).getName(),
                        departmentRepository.searhDepartmentById(employee.getDepartmentId()).getDescription()
                ),
                employeeRepository.getUser(employee.getId()).getRole(),
                employeeRepository.getUser(employee.getId()).getUsername(),
                employeeRepository.getUser(employee.getId()).getPassword()
        );
    }

    @Override
    @Transactional
    public EmployeeResponse saveEmployee(EmployeeCreationRequest employeeCreationRequest) {

        Employee employee = employeeRepository.saveEmployee(employeeCreationRequest);
        if (employee == null){
            return null;
        }
        User newUser = employeeRepository.createNewUser(employeeCreationRequest);

        return new EmployeeResponse(
                employee.getId(),
                employee.getEmployeeCode(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getPhone(),
                employee.getJoinDate(),
                new DepartmentResponse(
                        employee.getDepartmentId(),
                        departmentRepository.searhDepartmentById(employee.getDepartmentId()).getName(),
                        departmentRepository.searhDepartmentById(employee.getDepartmentId()).getDescription()
                ),
                newUser.getRole(),
                newUser.getUsername(),
                newUser.getPassword()
        );
    }

    @Override
    @Transactional
    public EmployeeResponse updateEmployee(Long id, EmployeeCreationRequest employeeCreationRequest) {
        Employee employee = employeeRepository.updateEmployee(id, employeeCreationRequest);
        if (employee == null){
            return null;
        }

        User updatedUser = employeeRepository.updateUser(id, employeeCreationRequest);

        return new EmployeeResponse(
                employee.getId(),
                employee.getEmployeeCode(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getPhone(),
                employee.getJoinDate(),
                new DepartmentResponse(
                        employee.getDepartmentId(),
                        departmentRepository.searhDepartmentById(employee.getDepartmentId()).getName(),
                        departmentRepository.searhDepartmentById(employee.getDepartmentId()).getDescription()
                ),
                updatedUser.getRole(),
                updatedUser.getUsername(),
                updatedUser.getPassword()
        );
    }

    @Override
    public Boolean deactivateEmployee(Long id) {
        return employeeRepository.deactivateEmployee(id);
    }

    @Override
    public UserResponse getUserDetails(LoginRequest loginRequest) {
        User user = employeeRepository.getUser(loginRequest.getUserName());
        if (user != null){
            if (user.getPassword().equals(loginRequest.getPassword())){
                return new UserResponse(
                        user.getId(),
                        user.getUsername(),
                        user.getRole()
                );
            }
        }
        return null;
    }

    @Override
    public Integer getEmployeeCount() {
        return employeeRepository.getEmployeeCount();
    }
}
