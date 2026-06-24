package edu.icet.ecom.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeCreationRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String departmentName;
    private String role;
}
