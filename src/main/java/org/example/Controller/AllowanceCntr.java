package org.example.Controller;


import org.example.Model.AllowanceModel;
import org.example.Model.PremiumModel;
import org.example.services.AllowanceService;
import org.example.services.PremiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("allowance")
public class AllowanceCntr {

    @Autowired
    AllowanceService allowanceService;

    @GetMapping("/get/{id}")
    public ResponseEntity<List<AllowanceModel>> getByEmployeeId(@PathVariable(value = "id") long id){

        List<AllowanceModel> allowanceModels = allowanceService.findByEmployeeId(id);
        return new ResponseEntity<>(allowanceModels, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addSalary(@RequestBody AllowanceModel allowanceModel){
        allowanceService.save(allowanceModel);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<AllowanceModel> edit(@PathVariable(value = "id") long id,@RequestBody AllowanceModel allowanceModel){
        AllowanceModel updateAllowance = allowanceService.edit(allowanceModel);
        return new ResponseEntity<>(updateAllowance, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") long id){
        allowanceService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
