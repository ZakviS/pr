package org.example.Repository;


import org.example.Entity.Allowance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllowanceRepo extends JpaRepository<Allowance,Long> {
}
