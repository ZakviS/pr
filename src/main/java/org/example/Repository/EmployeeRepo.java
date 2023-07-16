package org.example.Repository;

import org.example.Entity.Employee;
import org.example.Entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EmployeeRepo extends JpaRepository<Employee,Long> {

    Employee findByNameAndSurnameAndSecondSurnameAndPhoneNumberAndEmail(String name,String surname,String secondSurname,String phoneNumber,String email);

    List<Employee> findBySurname(String surname);

//    List<Employee> findBySurnameAndDismissalEqualsNull(String surname);

}
