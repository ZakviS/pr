package org.example.Repository;

import org.example.Entity.Premium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PremiumRepo extends JpaRepository<Premium,Long> {
}
