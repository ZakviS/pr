package org.example.services;

import org.example.Entity.Employee;
import org.example.Entity.Premium;
import org.example.Entity.Salary;
import org.example.Model.PremiumModel;
import org.example.Model.SalaryModel;
import org.example.Repository.EmployeeRepo;
import org.example.Repository.PremiumRepo;
import org.example.Repository.SalaryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PremiumService {

    @Autowired
    PremiumRepo premiumRepo;
    @Autowired
    public EmployeeRepo employeeRepo;

    public List<PremiumModel> findByEmployeeId(Long id){

        List<Premium> premiums ;
        premiums = premiumRepo.findAllByEmployee(employeeRepo.findByid(id));
        List<PremiumModel> premiumModels = entityInModel(premiums);
        return premiumModels;
    }

    public PremiumModel edit(PremiumModel premiumModel){
        List<PremiumModel> premiumModelList = new ArrayList<>();
        premiumModelList.add(premiumModel);
        Premium premium = modelInEntity(premiumModelList).get(0);
        Employee employee = employeeRepo.findByid(premiumModel.getEmployeeId());
        employee.addPremium(premium);
        employeeRepo.save(employee);
        return premiumModel;
    }

    public void save(PremiumModel premiumModel){
        List<PremiumModel> premiumModels = new ArrayList<>();
        premiumModels.add(premiumModel);
        Premium premium = modelInEntity(premiumModels).get(0);
        premiumRepo.save(premium);
    }

    public void delete(Long id){
        premiumRepo.deleteById(id);
    }

    public List<PremiumModel> entityInModel(List<Premium> premiums){
        List<PremiumModel> salaryDTOs = premiums.stream()
                .map(salary -> {
                    PremiumModel dto = new PremiumModel();
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
        return salaryDTOs;
    }

    public List<Premium> modelInEntity(List<PremiumModel> premiumModels){

        List<Premium> salaries = premiumModels.stream()
                .map(salaryModel -> {
                    Premium salary = new Premium();
                    salary.setId(salaryModel.getId());
                    salary.setSum((salaryModel.getSum()));
                    salary.setMonth(salaryModel.getMonth());
                    salary.setDateOfOrder(salaryModel.getDateOfOrder());
                    salary.setNumberOfOrder(salaryModel.getNumberOfOrder());
                    salary.setEmployee(employeeRepo.findByid(salaryModel.getEmployeeId()));
                    // Установите другие поля в DTO, если необходимо
                    return salary;
                })
                .collect(Collectors.toList());
        return salaries;
    }

}
