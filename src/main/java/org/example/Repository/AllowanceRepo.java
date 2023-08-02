package org.example.Repository;


import org.example.Entity.Allowance;
import org.example.Entity.Employee;
import org.example.Entity.Premium;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AllowanceRepo extends JpaRepository<Allowance,Long> {

    List<Allowance> findAllByEmployee(Employee employee);


}
