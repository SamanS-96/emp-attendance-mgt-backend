package edu.icet.ecom.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CheckInCreationRequest {
    private Long employeeId;
    private LocalDateTime checkInTime;
}
