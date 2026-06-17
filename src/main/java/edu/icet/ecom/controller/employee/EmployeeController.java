package edu.icet.ecom.controller.employee;

import edu.icet.ecom.entity.Employee;
import edu.icet.ecom.service.employee.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/get-all")
    List<Employee> getAll(){
        return employeeService.getAllEmployees();
    }
}
