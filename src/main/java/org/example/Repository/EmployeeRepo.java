package org.example.Repository;

import org.example.Entity.Employee;
import org.example.Entity.Position;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;


public interface EmployeeRepo extends JpaRepository<Employee,Long> {

    Employee findByNameAndSurnameAndSecondSurnameAndPhoneNumberAndEmail(String name,String surname,String secondSurname,String phoneNumber,String email);

    List<Employee> findBySurname(String surname);

    List<Employee> findByDismissalIsNullAndSurnameContainingIgnoreCase(String surname);
    List<Employee> findBySurnameContainingIgnoreCase(String surname);
    List<Employee> findByDismissalIsNotNullAndSurnameContainingIgnoreCase(String surname);

    List<Employee> findAllByDismissalIsNotNull();
    List<Employee> findAllByDismissalIsNull();

    @EntityGraph(value = "employee.noFetch", type = EntityGraph.EntityGraphType.FETCH)
    List<Employee> findAll();
}
