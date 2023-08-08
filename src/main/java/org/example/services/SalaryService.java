package org.example.services;

import org.example.Entity.Employee;
import org.example.Entity.Salary;
import org.example.Model.EmployeeModel;
import org.example.Model.SalaryModel;
import org.example.Repository.EmployeeRepo;
import org.example.Repository.SalaryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalaryService {

    @Autowired
    SalaryRepo salaryRepo;
    @Autowired
    public EmployeeRepo employeeRepo;

    public List<SalaryModel> findByEmployeeId(Long id){

        List<Salary> salaries ;
        salaries = salaryRepo.findAllByEmployee(employeeRepo.findByid(id));
        List<SalaryModel> salaryModels = entityInModel(salaries);
        return salaryModels;
    }

    public SalaryModel edit(SalaryModel salaryModel){
        List<SalaryModel> salaryModelList = new ArrayList<>();
        salaryModelList.add(salaryModel);
        Salary salary = modelInEntity(salaryModelList).get(0);
        Employee employee = employeeRepo.findByid(salaryModel.getEmployeeId());
        employee.addSalary(salary);
        employeeRepo.save(employee);
        return salaryModel;
    }

    public void save(SalaryModel salaryModel){
        List<SalaryModel> salaryModelList = new ArrayList<>();
        salaryModelList.add(salaryModel);
        Salary salary = modelInEntity(salaryModelList).get(0);
        salaryRepo.save(salary);
    }

    public void delete(Long id){
        salaryRepo.deleteById(id);
    }

    public List<SalaryModel> entityInModel(List<Salary> salaries){
        List<SalaryModel> salaryDTOs = salaries.stream()
                .map(salary -> {
                    SalaryModel dto = new SalaryModel();
                    dto.setId(salary.getId());
                    dto.setSum(salary.getSum());
                    dto.setMonth(salary.getMonth());
                    dto.setDateOfOrder(salary.getDateOfOrder());
                    dto.setNumberOfOrder(salary.getNumberOfOrder());
                    dto.setEmployeeId(salary.getEmployee().getId());
                    return dto;
                })
                .collect(Collectors.toList());
        return salaryDTOs;
    }

    public List<Salary> modelInEntity(List<SalaryModel> salaryModels){

        List<Salary> salaries = salaryModels.stream()
                .map(salaryModel -> {
                    Salary salary = new Salary();
                    salary.setId(salaryModel.getId());
                    salary.setSum((salaryModel.getSum()));
                    salary.setMonth(salaryModel.getMonth());
                    salary.setDateOfOrder(salaryModel.getDateOfOrder());
                    salary.setNumberOfOrder(salaryModel.getNumberOfOrder());
                    salary.setEmployee(employeeRepo.findByid(salaryModel.getEmployeeId()));
                    return salary;
                })
                .collect(Collectors.toList());
        return salaries;
    }


}
