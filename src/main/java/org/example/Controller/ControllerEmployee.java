package org.example.Controller;

import org.example.Entity.Employee;
import org.example.Entity.Salary;
import org.example.services.EmployeeService;

import java.time.LocalDate;

public class ControllerEmployee {

    Employee employee = new Employee();
    Salary salary = new Salary();

    private String nameOfPosition;

    public void setName(String name){
        employee.setName(name);
    }

    public void setSurname(String name){
        employee.setSurname(name);
    }
    public void setSecondSurname(String name){
        employee.setSecondSurname(name);
    }
    public void setBeginning(LocalDate localDate){
        employee.setBeginning(localDate);
    }
    public void setDismissal(LocalDate localDate){
        employee.setDismissal(localDate);
    }
    public void setPhoneNumber(String name){
        employee.setPhoneNumber(name);
    }
    public void setEmail(String name){
        employee.setEmail(name);
    }

    public void setSalary(Long sum) {
        salary.setSum(sum);
    }

    public void setDateSalary(LocalDate localDate){
        salary.setMonth(localDate);
    }

    public void setPosition(String name){
        nameOfPosition = name;
    }

    public void saveEmployee(){
        EmployeeService employeeService = new EmployeeService();
               employeeService.saveEmployee(employee,salary,nameOfPosition);
    }
}
