package edu.icet.ecom.controller.login;

import edu.icet.ecom.model.dto.request.LoginRequest;
import edu.icet.ecom.model.dto.response.UserResponse;
import edu.icet.ecom.service.employee.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

    private final EmployeeService employeeService;

    @PostMapping("/get-user")
    UserResponse getUserDetails(@RequestBody LoginRequest loginRequest){
        return employeeService.getUserDetails(loginRequest);
    }
}
