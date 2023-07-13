package org.example.Repository;

import org.example.Entity.Employee;
import org.example.Entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepo extends JpaRepository<Employee,Long> {

    Employee findByNameAndSurnameAndSecondSurnameAndPhoneNumberAndEmail(String name,String surname,String secondSurname,String phoneNumber,String email);

    Employee findBySurname(String surname);

}
