package edu.icet.ecom.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class LeaveRequest {
    private Long employeeId;
    private LocalDate fromDate;
    private LocalDate toDate;
    private String reason;
}
