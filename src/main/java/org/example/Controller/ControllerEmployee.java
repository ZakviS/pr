package org.example.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.example.Entity.*;
import org.example.Repository.EmployeeRepo;
import org.example.Repository.PositionRepo;
import org.example.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.Period;
import java.util.Map;

@Controller
public class ControllerEmployee {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
            private PositionRepo positionRepo;

    ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();

    @Autowired
    private EmployeeRepo employeeRepo;

    @GetMapping("/test")
    public String Save(Model model){
        Iterable<Position> positions = positionRepo.findAll();
        model.addAttribute("positions",positions);
        return "test";
    }

    @PostMapping("/test")
    public String Save(/*@RequestBody Employee employee*/@RequestParam Long id) throws JsonProcessingException {

        System.out.println(id);
        Position position = positionRepo.findByid(id);
        System.out.println(position);

        return "redirect:/test";
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
