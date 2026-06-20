package edu.icet.ecom.controller.attendance;

import edu.icet.ecom.model.dto.request.CheckInCreationRequest;
import edu.icet.ecom.model.dto.request.CheckOutCreationRequest;
import edu.icet.ecom.model.dto.response.AttendanceResponse;
import edu.icet.ecom.service.attendance.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attendance")
@CrossOrigin(origins = "http://localhost:4200")
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

    @GetMapping("/get-all")
    List<AttendanceResponse> getAll(){
        return attendanceService.getAllAttendance();
    }

    @GetMapping("/get-allByUserName")
    List<AttendanceResponse> getAllByUserName(@RequestParam String userName){
        return attendanceService.getAllAttendanceByUserName(userName);
    }

}
