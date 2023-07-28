package org.example.Repository;

import org.example.Entity.Employee;
import org.example.Entity.Position;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;


public interface EmployeeRepo extends JpaRepository<Employee,Long> {


    Page<Employee> findByDismissalIsNullAndSurnameContainingIgnoreCase(String surname,Pageable pageable);
    Page<Employee> findBySurnameContainingIgnoreCase(String surname,Pageable pageable);

    Page<Employee> findAllByDismissalIsNull(Pageable pageable);

    Page<Employee> findAll(Pageable pageable);

    Employee findByid(Long id);

}
