package org.example.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Entity.*;
import org.example.Model.EmployeeModel;
import org.example.Model.EmployeeSearchModel;
import org.example.Repository.EmployeeRepo;
import org.example.Repository.PositionRepo;
import org.example.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("employee")
public class ControllerEmployee {

    @Autowired
    private EmployeeService employeeService;


    //работает вывод списка больше ничего не риализовано
    @PostMapping("/add")
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee, @RequestParam Long idPosition){
        employeeService.saveEmployee(employee, idPosition);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //вроде все работает
    @GetMapping("/search")
    public ResponseEntity<List<EmployeeModel>> search(){
        List<EmployeeModel> employees = employeeService.findAll();

        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
//    @PostMapping("/search")
//    public ResponseEntity<List<Employee>> search(@RequestBody EmployeeSearchModel employeeSearchModel){
//        List<Employee> employees = employeeService.search(employeeSearchModel);
//        return new ResponseEntity<>(employees, HttpStatus.OK);
//    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
        employeeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //сделана штмл страница не полностью
    @PutMapping("/edit/{id}")
    public ResponseEntity<Employee> edit(@PathVariable(value = "id") long id,@RequestBody Employee employee){
        Employee updateEmployee = employeeService.updateEmployee(employee);
        return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
    }


}
