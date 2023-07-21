package org.example.services;

import org.example.Entity.Employee;
import org.example.Entity.Position;
import org.example.Model.EmployeeModel;
import org.example.Model.PositionModel;
import org.example.Repository.PositionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PositionService {

    @Autowired
    public PositionRepo positionRepo;


    public List<PositionModel> findAll(){
        List<Position> positions = positionRepo.findAll();
        List<PositionModel> positionModels = positions.stream()
                .map(employee -> {
                    PositionModel dto = new PositionModel();
                    dto.setId(employee.getId());
                    dto.setName(employee.getName());
                    dto.setBeginning(employee.getBeginning());
                    // Установите другие поля в DTO, если необходимо
                    return dto;
                })
                .collect(Collectors.toList());
        return positionModels;
    }

}
