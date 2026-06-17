package edu.icet.ecom.controller.employee;

import edu.icet.ecom.entity.Employee;
import edu.icet.ecom.service.employee.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/get/{id}")
    Employee getEmployee(@PathVariable Long id){
        return employeeService.getEmployeeById(id);
    }

    @PostMapping("/save")
    Employee saveEmployee(@RequestBody Employee employee){
        return employeeService.saveEmployee(employee);
    }

    @PutMapping("/update/{id}")
    Employee updateEmployee(
            @PathVariable Long id,
            @RequestBody Employee employee){

        return employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("/delete/{id}")
    Boolean deleteEmployee(@PathVariable Long id){
        return employeeService.deleteEmployee(id);
    }
}
