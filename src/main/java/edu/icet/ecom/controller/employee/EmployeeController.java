package edu.icet.ecom.controller.employee;

import edu.icet.ecom.enums.UserRole;
import edu.icet.ecom.model.dto.request.EmployeeCreationRequest;
import edu.icet.ecom.model.dto.response.EmployeeResponse;
import edu.icet.ecom.service.employee.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
@CrossOrigin(origins = "http://localhost:4200")
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

    @PutMapping("/deactivate/{id}")
    Boolean deactivateEmployee(@PathVariable Long id){
        return employeeService.deactivateEmployee(id);
    }

    @GetMapping("/roles")
    public List<String> getRoles(){
        return Arrays.stream(UserRole.values())
                .map(Enum::name)
                .toList();
    }

}
