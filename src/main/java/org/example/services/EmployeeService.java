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


    public void saveEmployee(EmployeeModel employeeModel){
        List<EmployeeModel> employeeModelList = new ArrayList<>();
        employeeModelList.add(employeeModel);
        System.out.println(employeeModelList);
        Employee employee = modelInEntity(employeeModelList).get(0);
        System.out.println(employee);
        employeeRepo.save(employee);

    }

    public EmployeeModel updateEmployee(EmployeeModel employeeModel){
        List<EmployeeModel> employeeModelList = new ArrayList<>();
        employeeModelList.add(employeeModel);
        Employee employee = modelInEntity(employeeModelList).get(0);
        employeeRepo.save(employee);
        return employeeModel;
    }

    public void delete(Long id){
            employeeRepo.deleteById(id);
    }

    public List<EmployeeModel> search(EmployeeSearchModel employeeSearchModel){
        List<Employee> employees;
//        if ("".equals(employeeSearchModel.getSurname()) || employeeSearchModel.getSurname() == null){
//            if (employeeSearchModel.isWorking()){
//                employees = employeeRepo.findAllByDismissalIsNull();
//            } else {
//                employees = employeeRepo.findAllByDismissalIsNotNull();
//            }
//        } else
//        if (employeeSearchModel.isWorking()){
//            employees = employeeRepo.findByDismissalIsNullAndSurnameContainingIgnoreCase(employeeSearchModel.getSurname());
//        } else {
//            employees = employeeRepo.findByDismissalIsNotNullAndSurnameContainingIgnoreCase(employeeSearchModel.getSurname());
//        }

        if ("".equals(employeeSearchModel.getSurname()) || employeeSearchModel.getSurname() == null){
            if (employeeSearchModel.isWorking()){
                employees = employeeRepo.findAllByDismissalIsNull();
            } else {
                employees = employeeRepo.findAll();
            }
        } else
        if (employeeSearchModel.isWorking()){
            employees = employeeRepo.findByDismissalIsNullAndSurnameContainingIgnoreCase(employeeSearchModel.getSurname());
        } else {
            employees = employeeRepo.findBySurnameContainingIgnoreCase(employeeSearchModel.getSurname());
        }

        List<EmployeeModel> employeeModels = entityInModel(employees);

        return employeeModels;
    }

    public List<EmployeeModel> findAll(){
        List<Employee> employees = employeeRepo.findAll();

        List<EmployeeModel> employeeModels = entityInModel(employees);

        return employeeModels;
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
                    // Установите другие поля в DTO, если необходимо
                    return dto;
                })
                .collect(Collectors.toList());
        return employeeDTOs;
    }

    public List<Employee> modelInEntity(List<EmployeeModel> employeeModels){



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
                    // Установите другие поля в DTO, если необходимо
                    return employee;
                })
                .collect(Collectors.toList());
        return employees;
    }

}
