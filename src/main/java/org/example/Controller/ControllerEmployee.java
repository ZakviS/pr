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
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class ControllerEmployee {

    @Autowired
    private EmployeeService employeeService;



    @GetMapping("/test")
    public String Save(Model model){
        return "test";
    }

    @PostMapping("/test")
    public String Save(@RequestParam String NameEmployee,@RequestParam String SurnameEmployee, @RequestParam String NamePosition, @RequestParam Long SumSalary, Model model){
        Employee employee = Employee.builder()
                .name(NameEmployee)
                .surname(SurnameEmployee)
                .build();
        Salary salary = Salary.builder()
                .sum(SumSalary)
                .build();
        if(employeeService.isEmployeeExist(SurnameEmployee)){
            employeeService.update(SurnameEmployee,salary,NamePosition);
        }else employeeService.saveEmployee(employee,salary,NamePosition);
        return "test";
    }

    @GetMapping("/delete")
    public String delete(Model model){
        return "delete";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long Id,Model model){
        employeeService.delete(Id);
        return "redirect:/nothing";
    }


}
