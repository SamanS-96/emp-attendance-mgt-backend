package edu.icet.ecom.controller.department;

import edu.icet.ecom.model.dto.request.DepartmentCreationRequest;
import edu.icet.ecom.model.dto.request.EmployeeCreationRequest;
import edu.icet.ecom.model.dto.response.DepartmentResponse;
import edu.icet.ecom.model.dto.response.EmployeeResponse;
import edu.icet.ecom.model.entity.Department;
import edu.icet.ecom.service.department.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping("/get-all")
    List<DepartmentResponse> getAll(){
        return departmentService.getAllDepartments();
    }

    @GetMapping("/get/{id}")
    DepartmentResponse getDepartment(@PathVariable Long id){
        return departmentService.getDepartmentById(id);
    }

    @PostMapping("/save")
    DepartmentResponse saveDepartment(@RequestBody DepartmentCreationRequest departmentCreationRequest){
        return departmentService.saveDepartment(departmentCreationRequest);
    }

    @PutMapping("/update/{id}")
    DepartmentResponse updateDepartment(
            @PathVariable Long id,
            @RequestBody DepartmentCreationRequest departmentCreationRequest){

        return departmentService.updateDepartment(id, departmentCreationRequest);
    }

    @DeleteMapping("/delete/{id}")
    Boolean deleteDepartment(@PathVariable Long id){
        return departmentService.deleteDepartment(id);
    }
}
