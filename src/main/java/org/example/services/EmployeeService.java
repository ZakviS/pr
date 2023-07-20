package org.example.services;

import org.aspectj.apache.bcel.classfile.Module;
import org.example.Entity.*;
import org.example.Model.EmployeeModel;
import org.example.Model.EmployeeSearchModel;
import org.example.Repository.*;
//import org.example.Util.HibernateUtil;
//import org.hibernate.query.Query;
import org.example.Util.MapperUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    public PositionRepo positionRepo;
    @Autowired
    public EmployeeRepo employeeRepo;
//    @Autowired
//    public SalaryRepo salaryRepo;
//    @Autowired
//    public AllowanceRepo allowanceRepo;
//    @Autowired
//    public PremiumRepo premiumRepo;
    @Autowired
    private ModelMapper modelMapper;


    public void saveEmployee(Employee employee,Long idPosition){
                Position positions = positionRepo.findByid(idPosition);
                employee.setPosition(positions);
                employeeRepo.save(employee);
    }

    public Employee updateEmployee(Employee employee){
        return employeeRepo.save(employee);
    }

    public void delete(Long id){
            employeeRepo.deleteById(id);
    }

    public List<Employee> search(EmployeeSearchModel employeeSearchModel){
        System.out.println(employeeSearchModel);
        List<Employee> employees;
        if ("".equals(employeeSearchModel.getSurname()) || employeeSearchModel.getSurname() == null){
            if (employeeSearchModel.isWorking()){
                employees = employeeRepo.findAllByDismissalIsNull();
            } else {
                employees = employeeRepo.findAllByDismissalIsNotNull();
            }
        } else
        if (employeeSearchModel.isWorking()){
            employees = employeeRepo.findByDismissalIsNullAndSurname(employeeSearchModel.getSurname());
        } else {
            employees = employeeRepo.findByDismissalIsNotNullAndSurname(employeeSearchModel.getSurname());
        }

        return employees;
    }

    public List<EmployeeModel> findAll(){
        List<Employee> employees = employeeRepo.findAll();

        List<EmployeeModel> employeeDTOs = employees.stream()
                .map(employee -> {
                    EmployeeModel dto = new EmployeeModel();
                    dto.setId(employee.getId());
                    dto.setName(employee.getName());
                    dto.setSurname(employee.getSurname());
                    dto.setBeginning(employee.getBeginning());
                    dto.setDismissal(employee.getDismissal());
                    dto.setPhoneNumber(employee.getPhoneNumber());
                    dto.setEmail(employee.getEmail());
                    // Установите другие поля в DTO, если необходимо
                    return dto;
                })
                .collect(Collectors.toList());

        return employeeDTOs;
    }

//    public List<Employee> findAll(){
//
//        List<Employee> users = employeeRepo.findAll();
//        return MapperUtil.convertList(users,this::convertToNoPos);
//    }
//
//    private Employee convertToNoPos(Employee employee) {
//        modelMapper.typeMap(Employee.class, Employee.class).addMappings(mapper -> mapper.skip(Employee::setPosition));
//        return modelMapper.map(employee, Employee.class);
//    }

////many
//    private List<EmployeeModel> convertToEmployeeModel(Employee employee) {
//        List<EmployeeModel> employeeModel = modelMapper.map(employee, List<EmployeeModel.class>);
//
//        return employeeModel;
//    }
// one
//    private PosotionModel convertToPositionModel(Position position) {
//        return modelMapper.map(position, PosotionModel.class);
//    }

}
