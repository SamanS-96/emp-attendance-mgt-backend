package edu.icet.ecom.schedulers;

import edu.icet.ecom.service.attendance.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AbsentEmployeeScheduler {

    private final AttendanceService attendanceService;

    @Scheduled(cron = "0 0 13 * * *")
    void setEmpStatusForAbsent(){
        attendanceService.setEmpStatusForAbsent();
    }
}
