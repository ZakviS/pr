package org.example.Controller;

import org.example.Model.EmployeeModel;
import org.example.Model.EmployeeResponse;
import org.example.Model.EmployeeSearchModel;
import org.example.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employee")
public class EmployeeCntr {

    @Autowired
    private EmployeeService employeeService;


    //работает вывод списка больше ничего не риализовано
    @PostMapping("/add")
    public ResponseEntity<?> addEmployee(@RequestBody EmployeeModel employee){
        employeeService.saveEmployee(employee);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    //вроде все работает
    @PostMapping("/search")
    public ResponseEntity<EmployeeResponse> search(@RequestBody EmployeeSearchModel employeeSearchModel){
        System.out.println(employeeSearchModel);

        EmployeeResponse employees = employeeService.findAll(employeeSearchModel);

        return new ResponseEntity<>(employees, HttpStatus.OK);
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
        employeeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping("/edit/{id}")
    public ResponseEntity<EmployeeModel> edit(@PathVariable(value = "id") long id,@RequestBody EmployeeModel employee){
        EmployeeModel updateEmployee = employeeService.updateEmployee(employee);
        return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
    }


}
