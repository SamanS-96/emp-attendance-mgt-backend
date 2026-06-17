package edu.icet.ecom.controller.employee;

import edu.icet.ecom.model.dto.request.EmployeeCreationRequest;
import edu.icet.ecom.model.dto.response.EmployeeResponse;
import edu.icet.ecom.model.entity.Employee;
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
    List<EmployeeResponse> getAll(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/get/{id}")
    EmployeeResponse getEmployee(@PathVariable Long id){
        return employeeService.getEmployeeById(id);
    }

    @PostMapping("/save")
    EmployeeResponse saveEmployee(@RequestBody EmployeeCreationRequest employeeCreationRequest){
        return employeeService.saveEmployee(employeeCreationRequest);
    }

    @PutMapping("/update/{id}")
    EmployeeResponse updateEmployee(
            @PathVariable Long id,
            @RequestBody EmployeeCreationRequest employeeCreationRequest){

        return employeeService.updateEmployee(id, employeeCreationRequest);
    }

    @DeleteMapping("/delete/{id}")
    Boolean deleteEmployee(@PathVariable Long id){
        return employeeService.deleteEmployee(id);
    }
}
