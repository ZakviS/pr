package org.example.Repository;

import org.example.Entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaryRepo  extends JpaRepository<Salary,Long> {
}
