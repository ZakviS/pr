package org.example.Repository;

import org.example.Entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EmployeeRepo extends JpaRepository<Employee,Long> {

    Employee findByNameAndSurnameAndSecondSurnameAndPhoneNumberAndEmail(String name,String surname,String secondSurname, String phoneNumber,String email);
    Page<Employee> findByDismissalIsNullAndSurnameContainingIgnoreCase(String surname,Pageable pageable);
    Page<Employee> findBySurnameContainingIgnoreCase(String surname,Pageable pageable);

    Page<Employee> findAllByDismissalIsNull(Pageable pageable);

    Page<Employee> findAll(Pageable pageable);

    List<Employee> findAll();

    Employee findByid(Long id);

}
