package edu.icet.ecom.controller.leave;

import edu.icet.ecom.model.dto.request.LeaveRequest;
import edu.icet.ecom.model.dto.response.AttendanceResponse;
import edu.icet.ecom.model.dto.response.LeaveResponse;
import edu.icet.ecom.service.leave.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/leave")
@CrossOrigin(origins = "http://localhost:4200")
public class LeaveController {

    private final LeaveService leaveService;

    @PostMapping("/leave-request")
    Boolean createLeave(@RequestBody LeaveRequest leaveRequest){
        return leaveService.createLeave(leaveRequest);
    }

    @PutMapping("/leave-update/{id}")
    Boolean updateLeave(
            @PathVariable Long id,
            @RequestBody LeaveRequest leaveRequest){
        return leaveService.updateLeave(id, leaveRequest);
    }

    @GetMapping("/get-all")
    List<LeaveResponse> getAll(){
        return leaveService.geAll();
    }

    @GetMapping("/get/{id}")
    LeaveResponse getById(@PathVariable Long id){
        return leaveService.geById(id);
    }

    @GetMapping("/get-allByUserName")
    List<LeaveResponse> getAllByUserName(@RequestParam String userName){
        return leaveService.getAllLeavesByUserName(userName);
    }
}
