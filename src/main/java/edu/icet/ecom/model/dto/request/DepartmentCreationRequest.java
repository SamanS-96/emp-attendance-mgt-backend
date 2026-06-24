package edu.icet.ecom.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DepartmentCreationRequest {
    private String name;
    private String description;
}
