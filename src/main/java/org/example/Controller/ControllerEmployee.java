package org.example.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Entity.*;
import org.example.Repository.EmployeeRepo;
import org.example.Repository.PositionRepo;
import org.example.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ControllerEmployee {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private PositionRepo positionRepo;
    ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();

    @Autowired
    private EmployeeRepo employeeRepo;

    @GetMapping("/add")
    public String Save(Model model){
        Iterable<Position> positions = positionRepo.findAll();
        model.addAttribute("positions",positions);
        return "add";
    }


    @PostMapping("/add")
    public String Save(/*@RequestBody Employee employee*/@RequestParam Long id) throws JsonProcessingException {

        System.out.println(id);
        Position position = positionRepo.findByid(id);
        System.out.println(position);

        return "redirect:/add";
    }


    @GetMapping("/search")
    public String Search(Model model){
        Iterable<Employee> employees = employeeRepo.findAll();
        model.addAttribute("employees",employees);
        return "search";
    }

    @PostMapping("/search")
    public String Search(@RequestParam String surname, @RequestParam Boolean work, Model model){
        List <Employee> employeeList = new ArrayList<>();
        List<Employee> employees = null;
        if(surname == ""){
            employees = employeeRepo.findAll();
        }
        else {
            employees = employeeRepo.findBySurname(surname);
        }

        if (!work){
            for (int i = 0;i < employees.size(); i++){
                if(employees.get(i).getDismissal() != null){
                    employeeList.add(employees.get(i));
                }
            }
        } else {
            for (int i = 0;i < employees.size(); i++){
                if(employees.get(i).getDismissal() == null){
                    employeeList.add(employees.get(i));
                }
            }
        }
        model.addAttribute("employees", employeeList);
        return "search";
    }


    @PostMapping("/delete/{id}/")
    public String delete(@PathVariable(value = "id") Long id){
        employeeService.delete(id);
        return "redirect:/search";
    }

    @GetMapping("/edit/{id}/")
    public String Edit(@PathVariable(value = "id") long id, Model model){
        if(!employeeRepo.existsById(id)){
            return "redirect:/search";
        }

        Optional<Employee> employee = employeeRepo.findById(id);
        ArrayList<Employee> res = new ArrayList<>();
        employee.ifPresent(res::add);

        //Position position = res.get(0).getPosition();
        Iterable<Position> positions = positionRepo.findAll();
        model.addAttribute("emp",res);
        model.addAttribute("pos",positions);
        return "edit";
    }

    @PostMapping("/edit/{id}/")
    public String Edit(@PathVariable(value = "id") long id, @RequestParam String name,@RequestParam String surname,@RequestParam Long Id, Model model){

        return "redirect:/blog";
    }


}
