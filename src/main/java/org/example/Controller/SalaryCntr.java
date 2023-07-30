package org.example.Controller;

import jakarta.persistence.Access;
import org.example.Entity.Employee;
import org.example.Entity.Salary;
import org.example.Model.EmployeeModel;
import org.example.Model.SalaryModel;
import org.example.services.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("salary")
public class SalaryCntr {

    @Autowired
    SalaryService salaryService;

    @GetMapping("/get/{id}")
    public ResponseEntity<List<SalaryModel>> getByEmployeeId(@PathVariable(value = "id") long id){

        List<SalaryModel> salaryModels = salaryService.findByEmployeeId(id);
        System.out.println(salaryModels);
        return new ResponseEntity<>(salaryModels, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addSalary(@RequestBody SalaryModel salaryModel){
        salaryService.save(salaryModel);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<SalaryModel> edit(@PathVariable(value = "id") long id,@RequestBody SalaryModel salaryModel){
        SalaryModel updateSalary = salaryService.edit(salaryModel);
        return new ResponseEntity<>(updateSalary, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") long id){
        salaryService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
