package edu.icet.ecom.controller.attendance;

import edu.icet.ecom.model.dto.request.CheckInCreationRequest;
import edu.icet.ecom.model.dto.request.CheckOutCreationRequest;
import edu.icet.ecom.service.attendance.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping("/check-in")
    Boolean saveCheckIn(@RequestBody CheckInCreationRequest checkInCreationRequest){
        return attendanceService.saveCheckIn(checkInCreationRequest);
    }

    @PutMapping("/check-out")
    Boolean saveCheckOut(@RequestBody CheckOutCreationRequest checkOutCreationRequest){
        return attendanceService.saveCheckOut(checkOutCreationRequest);
    }
}
