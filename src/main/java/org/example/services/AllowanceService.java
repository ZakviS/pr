package org.example.services;

import org.example.Entity.Allowance;
import org.example.Entity.Employee;
import org.example.Entity.Salary;
import org.example.Model.AllowanceModel;
import org.example.Model.SalaryModel;
import org.example.Repository.AllowanceRepo;
import org.example.Repository.EmployeeRepo;
import org.example.Repository.SalaryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AllowanceService {

    @Autowired
    AllowanceRepo allowanceRepo;
    @Autowired
    public EmployeeRepo employeeRepo;

    public List<AllowanceModel> findByEmployeeId(Long id){

        List<Allowance> allowances ;
        allowances = allowanceRepo.findAllByEmployee(employeeRepo.findByid(id));
        List<AllowanceModel> allowanceModels = entityInModel(allowances);
        return allowanceModels;
    }

    public AllowanceModel edit(AllowanceModel allowanceModel){
        List<AllowanceModel> allowanceModelList = new ArrayList<>();
        allowanceModelList.add(allowanceModel);
        Allowance allowance = modelInEntity(allowanceModelList).get(0);
        Employee employee = employeeRepo.findByid(allowanceModel.getEmployeeId());
        employee.addAllowance(allowance);
        employeeRepo.save(employee);
        return allowanceModel;
    }

    public void save(AllowanceModel allowanceModel){
        List<AllowanceModel> allowanceModelList = new ArrayList<>();
        allowanceModelList.add(allowanceModel);
        Allowance allowance = modelInEntity(allowanceModelList).get(0);
        allowanceRepo.save(allowance);
    }

    public void delete(Long id){
        allowanceRepo.deleteById(id);
    }

    public List<AllowanceModel> entityInModel(List<Allowance> allowances){
        List<AllowanceModel> allowanceModels = allowances.stream()
                .map(salary -> {
                    AllowanceModel dto = new AllowanceModel();
                    dto.setId(salary.getId());
                    dto.setSum(salary.getSum());
                    dto.setMonth(salary.getMonth());
                    dto.setDateOfOrder(salary.getDateOfOrder());
                    dto.setNumberOfOrder(salary.getNumberOfOrder());
                    dto.setEmployeeId(salary.getEmployee().getId());
                    // Установите другие поля в DTO, если необходимо
                    return dto;
                })
                .collect(Collectors.toList());
        return allowanceModels;
    }

    public List<Allowance> modelInEntity(List<AllowanceModel> allowanceModels){

        List<Allowance> allowances = allowanceModels.stream()
                .map(salaryModel -> {
                    Allowance allowance = new Allowance();
                    allowance.setId(salaryModel.getId());
                    allowance.setSum((salaryModel.getSum()));
                    allowance.setMonth(salaryModel.getMonth());
                    allowance.setDateOfOrder(salaryModel.getDateOfOrder());
                    allowance.setNumberOfOrder(salaryModel.getNumberOfOrder());
                    allowance.setEmployee(employeeRepo.findByid(salaryModel.getEmployeeId()));
                    // Установите другие поля в DTO, если необходимо
                    return allowance;
                })
                .collect(Collectors.toList());
        return allowances;
    }


}
