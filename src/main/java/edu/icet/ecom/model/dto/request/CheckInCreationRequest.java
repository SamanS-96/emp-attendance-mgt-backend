package edu.icet.ecom.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CheckInCreationRequest {
    private String userName;
}
