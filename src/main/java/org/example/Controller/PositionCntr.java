package org.example.Controller;

import org.example.Entity.Position;
import org.example.Model.EmployeeModel;
import org.example.Model.PositionModel;
import org.example.services.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("position")
public class PositionCntr {

    @Autowired
    PositionService positionService;

    @GetMapping("/all")
    public ResponseEntity<List<PositionModel>> search(){
        List<PositionModel> positions = positionService.findAll();
        return new ResponseEntity<>(positions, HttpStatus.OK);
    }


}
