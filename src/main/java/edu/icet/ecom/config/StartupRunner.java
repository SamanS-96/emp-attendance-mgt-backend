package edu.icet.ecom.config;

import edu.icet.ecom.model.dto.request.EmployeeCreationRequest;
import edu.icet.ecom.model.dto.response.EmployeeResponse;
import edu.icet.ecom.service.attendance.AttendanceService;
import edu.icet.ecom.service.employee.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StartupRunner implements ApplicationRunner {

    private final EmployeeService employeeService;
    private final AttendanceService attendanceService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<EmployeeResponse> employeeResponseList = employeeService.getAllEmployees();
        List<Long> employeeIdList = new ArrayList<>();
        employeeResponseList.forEach(employeeResponse -> {
            employeeIdList.add(employeeResponse.getId());
        });

        Boolean isInsertedEmployeesToAttendance = attendanceService.isInsertedEmployeesToAttendance();
        if (!isInsertedEmployeesToAttendance){
            attendanceService.insertEmployeeIdsToAttendance(employeeIdList);
        }
    }
}
