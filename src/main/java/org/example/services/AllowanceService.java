package org.example.services;

import org.example.Entity.Allowance;
import org.example.Entity.Employee;
import org.example.Entity.Salary;
import org.example.Exceptions.BusinessException;
import org.example.Exceptions.NotExistException;
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

        try {
            List<Allowance> allowances ;
            allowances = allowanceRepo.findAllByEmployee(employeeRepo.findByid(id));
            List<AllowanceModel> allowanceModels = entityInModel(allowances);
            return allowanceModels;
        } catch (BusinessException e) {
            throw e;
        }
    }

    public AllowanceModel edit(AllowanceModel allowanceModel){
        try {
            List<AllowanceModel> allowanceModelList = new ArrayList<>();
            allowanceModelList.add(allowanceModel);
            Allowance allowance = modelInEntity(allowanceModelList).get(0);
            Employee employee = employeeRepo.findByid(allowanceModel.getEmployeeId());
            employee.addAllowance(allowance);
            employeeRepo.save(employee);
            return allowanceModel;
        } catch (Exception exception){
            throw new BusinessException(exception.getMessage());
        }
    }

    public void save(AllowanceModel allowanceModel){
        try {
            List<AllowanceModel> allowanceModelList = new ArrayList<>();
            allowanceModelList.add(allowanceModel);
            Allowance allowance = modelInEntity(allowanceModelList).get(0);
            allowanceRepo.save(allowance);
        } catch (Exception exception){
            throw new BusinessException(exception.getMessage());
        }
    }

    public void delete(Long id){
        if(!allowanceRepo.existsById(id)){
            throw new NotExistException("Allowance doesnt exist");
        }
        try {
            allowanceRepo.deleteById(id);
        } catch (Exception exception){
            throw new BusinessException(exception.getMessage());
        }

    }

    public List<AllowanceModel> entityInModel(List<Allowance> allowances){
        try {
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
        } catch (Exception exception){
            throw new BusinessException(exception.getMessage());
        }
    }

    public List<Allowance> modelInEntity(List<AllowanceModel> allowanceModels){

        try {
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
        } catch (Exception exception){
            throw new BusinessException(exception.getMessage());
        }
    }


}
