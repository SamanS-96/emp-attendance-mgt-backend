package edu.icet.ecom.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class EmployeeCreationRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Long departmentId;
}
