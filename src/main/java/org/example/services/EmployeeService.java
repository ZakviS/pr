package org.example.services;

import org.example.Entity.*;
import org.example.Exceptions.BusinessException;
import org.example.Exceptions.EmployeeExistException;
import org.example.Exceptions.EmployeeNotExistException;
import org.example.Model.EmployeeModel;
import org.example.Model.EmployeeResponse;
import org.example.Model.EmployeeSearchModel;
import org.example.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    public PositionRepo positionRepo;
    @Autowired
    public EmployeeRepo employeeRepo;



    public void saveEmployee(EmployeeModel employeeModel){

        try{

        List<EmployeeModel> employeeModelList = new ArrayList<>();
        employeeModelList.add(employeeModel);
        Employee employee = modelInEntity(employeeModelList).get(0);
        if(employeeRepo.findByNameAndSurnameAndSecondSurnameAndPhoneNumberAndEmail(employee.getName(), employeeModel.getSurname(), employeeModel.getSecondSurname(), employeeModel.getPhoneNumber(), employeeModel.getEmail()) != null){
            throw new EmployeeExistException("Employee exist");
        }
        employeeRepo.save(employee);
        } catch (BusinessException e){
            throw e;
        }

    }

    public EmployeeModel updateEmployee(EmployeeModel employeeModel){
        try{
        List<EmployeeModel> employeeModelList = new ArrayList<>();
        employeeModelList.add(employeeModel);
        Employee employee = modelInEntity(employeeModelList).get(0);
        employeeRepo.save(employee);
        return employeeModel;
        } catch (BusinessException e){
            throw e;
        }

    }

    public void delete(Long id){
        if(!employeeRepo.existsById(id)){
            throw new EmployeeNotExistException("Employee doesnt exist");
        }
        try {
            employeeRepo.deleteById(id);
        } catch (BusinessException exception){
            throw exception;
        }

    }


    public EmployeeResponse findAll(EmployeeSearchModel employeeSearchModel){

        try {

        Page<Employee> employees;

        if ("".equals(employeeSearchModel.getSurname()) || employeeSearchModel.getSurname() == null){
            if (employeeSearchModel.isWorking()){
                employees = employeeRepo.findAllByDismissalIsNull(PageRequest.of(employeeSearchModel.getPage(), employeeSearchModel.getElementPerPage(), employeeSearchModel.buildSort()));
            } else {
                employees = employeeRepo.findAll(PageRequest.of(employeeSearchModel.getPage(), employeeSearchModel.getElementPerPage(), employeeSearchModel.buildSort()));
            }
        } else
        if (employeeSearchModel.isWorking()){
            employees = employeeRepo.findByDismissalIsNullAndSurnameContainingIgnoreCase(employeeSearchModel.getSurname(),PageRequest.of(employeeSearchModel.getPage(), employeeSearchModel.getElementPerPage(), employeeSearchModel.buildSort()));
        } else {
            employees = employeeRepo.findBySurnameContainingIgnoreCase(employeeSearchModel.getSurname(),PageRequest.of(employeeSearchModel.getPage(), employeeSearchModel.getElementPerPage(), employeeSearchModel.buildSort()));
        }


        List<EmployeeModel> employeeModel = pageInModel(employees);
        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setEmployee(employeeModel);
        employeeResponse.setPageNo(employees.getNumber());
        employeeResponse.setPageSize(employees.getSize());
        employeeResponse.setTotalElements(employees.getTotalElements());
        employeeResponse.setTotalPages(employees.getTotalPages());
        employeeResponse.setLast(employees.isLast());

        return employeeResponse;
        } catch (BusinessException e){
            throw e;
        }
    }

    public List<EmployeeModel> entityInModel(List<Employee> employees){
        List<EmployeeModel> employeeDTOs = employees.stream()
                .map(employee -> {
                    EmployeeModel dto = new EmployeeModel();
                    dto.setId(employee.getId());
                    dto.setName(employee.getName());
                    dto.setSurname(employee.getSurname());
                    dto.setSecondSurname(employee.getSecondSurname());
                    dto.setBeginning(employee.getBeginning());
                    dto.setDismissal(employee.getDismissal());
                    dto.setPhoneNumber(employee.getPhoneNumber());
                    dto.setEmail(employee.getEmail());
                    dto.setPositionId(employee.getPosition().getId());
                    return dto;
                })
                .collect(Collectors.toList());
        return employeeDTOs;
    }

    public List<Employee> modelInEntity(List<EmployeeModel> employeeModels){
        try{
        List<Employee> employees = employeeModels.stream()
                .map(employeeModel -> {
                    Employee employee = new Employee();
                    employee.setId(employeeModel.getId());
                    employee.setName(employeeModel.getName());
                    employee.setSurname(employeeModel.getSurname());
                    employee.setSecondSurname(employeeModel.getSecondSurname());
                    employee.setBeginning(employeeModel.getBeginning());
                    employee.setDismissal(employeeModel.getDismissal());
                    employee.setPhoneNumber(employeeModel.getPhoneNumber());
                    employee.setEmail(employeeModel.getEmail());
                    employee.setPosition(positionRepo.findByid(employeeModel.getPositionId()));
                    return employee;
                })
                .collect(Collectors.toList());
        return employees;
        } catch (BusinessException e){
            throw e;
        }
    }

    public List<EmployeeModel> pageInModel(Page<Employee> employees){
        try{
        List<EmployeeModel> employeeDTOs = employees.stream()
                .map(employee -> {
                    EmployeeModel dto = new EmployeeModel();
                    dto.setId(employee.getId());
                    dto.setName(employee.getName());
                    dto.setSurname(employee.getSurname());
                    dto.setSecondSurname(employee.getSecondSurname());
                    dto.setBeginning(employee.getBeginning());
                    dto.setDismissal(employee.getDismissal());
                    dto.setPhoneNumber(employee.getPhoneNumber());
                    dto.setEmail(employee.getEmail());
                    dto.setPositionId(employee.getPosition().getId());
                    return dto;
                })
                .collect(Collectors.toList());
        return employeeDTOs;
        } catch (BusinessException e){
            throw e;
        }
    }

}
