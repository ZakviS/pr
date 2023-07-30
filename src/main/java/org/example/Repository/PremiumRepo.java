package org.example.Repository;

import org.example.Entity.Employee;
import org.example.Entity.Premium;
import org.example.Entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PremiumRepo extends JpaRepository<Premium,Long> {

    List<Premium> findAllByEmployee(Employee employee);


}
