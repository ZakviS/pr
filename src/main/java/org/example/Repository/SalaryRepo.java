package org.example.Repository;

import org.example.Entity.Employee;
import org.example.Entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalaryRepo  extends JpaRepository<Salary,Long> {


    List<Salary> findAllByEmployee(Employee employee);

}
