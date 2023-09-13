package org.example.Repository;

import org.example.Entity.Person;
import org.example.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PersonRepo extends JpaRepository<Person,Long> {

    Optional<Person> findOneByLogin(String login);

    long countByRoles(Role role);

    Optional<Person> findByToken(String token);


//    @Query("select p from Person p join fetch p.roles where p.token = ?1")
//    Optional<Person> findOneByToken(String token);

    @Query("select p from Person p where p.token is not null and p.tokenExpirationDate < ?1")
    List<Person> findAllByTokenNotNullAndTokenExpirationDateLessThan(Date date);

}
