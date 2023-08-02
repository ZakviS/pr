package org.example.Controller;

import org.example.Model.PremiumModel;
import org.example.Model.SalaryModel;
import org.example.services.PremiumService;
import org.example.services.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("premium")
public class PremiumCntr {

    @Autowired
    PremiumService premiumService;

    @GetMapping("/get/{id}")
    public ResponseEntity<List<PremiumModel>> getByEmployeeId(@PathVariable(value = "id") long id){

        List<PremiumModel> PremiumModel = premiumService.findByEmployeeId(id);
        System.out.println(PremiumModel);
        return new ResponseEntity<>(PremiumModel, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addSalary(@RequestBody PremiumModel premiumModel){
        premiumService.save(premiumModel);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<PremiumModel> edit(@PathVariable(value = "id") long id,@RequestBody PremiumModel premiumModel){
        PremiumModel updatePremium = premiumService.edit(premiumModel);
        return new ResponseEntity<>(updatePremium, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") long id){
        premiumService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
