package org.example.Controller;

import org.example.Model.EmployeeModel;
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
        System.out.println(employee);
        employeeService.saveEmployee(employee);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //вроде все работает
    @GetMapping("/search")
    public ResponseEntity<List<EmployeeModel>> search(){
        List<EmployeeModel> employees = employeeService.findAll();

        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<EmployeeModel>> search(@RequestBody EmployeeSearchModel employeeSearchModel){

        List<EmployeeModel> employees = employeeService.search(employeeSearchModel);

        return new ResponseEntity<>(employees, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
        employeeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //сделана штмл страница не полностью
//    @PutMapping("/edit/{id}")
//    public ResponseEntity<Employee> edit(@PathVariable(value = "id") long id,@RequestBody Employee employee){
//        Employee updateEmployee = employeeService.updateEmployee(employee);
//        return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
//    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<EmployeeModel> edit(@PathVariable(value = "id") long id,@RequestBody EmployeeModel employee){
        EmployeeModel updateEmployee = employeeService.updateEmployee(employee);
        return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
    }


}
