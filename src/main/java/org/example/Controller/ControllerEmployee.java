package org.example.Controller;

import org.example.Entity.Employee;
import org.example.Entity.Salary;
import org.example.Repository.PositionRepo;
import org.example.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@Controller
public class ControllerEmployee {

    @Autowired
    private EmployeeService employeeService;



    @PostMapping("/test")
    public String blog(){
        employeeService.saveEmployee();
        return "test";
    }



}
