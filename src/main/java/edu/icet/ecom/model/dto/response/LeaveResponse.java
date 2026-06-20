package edu.icet.ecom.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class LeaveResponse {
    private Long id;
    private String userName;
    private LocalDate fromDate;
    private LocalDate toDate;
    private String reason;
    private String status;
}
