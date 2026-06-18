package edu.icet.ecom.controller.leave;

import edu.icet.ecom.model.dto.request.LeaveRequest;
import edu.icet.ecom.service.leave.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/leave")
public class LeaveController {

    private final LeaveService leaveService;

    @PostMapping("/leave-request")
    Boolean createLeave(@RequestBody LeaveRequest leaveRequest){
        return leaveService.createLeave(leaveRequest);
    }
}
