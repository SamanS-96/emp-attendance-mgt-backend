package edu.icet.ecom.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TodayAttendance {
    private Integer presentToday;
    private Integer absentToday;
    private String myStatus;
}
