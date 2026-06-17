package edu.icet.ecom.controller.department;

import edu.icet.ecom.entity.Department;
import edu.icet.ecom.service.department.DepartmentService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping("/get-all")
    List<Department> getAll(){
        return departmentService.getAllDepartments();
    }
}
