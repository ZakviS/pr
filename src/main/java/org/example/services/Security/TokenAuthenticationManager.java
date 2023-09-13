package org.example.services.Security;

import org.example.Entity.Person;
import org.example.Repository.PersonRepo;
import org.example.Util.TokenAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class TokenAuthenticationManager implements AuthenticationManager {
    @Autowired
    private PersonRepo personRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication instanceof TokenAuthentication) {
            System.out.println("auth man");
            String token = ((TokenAuthentication) authentication).getToken();
//            System.out.println(token1);
//            String token = token1.substring(7);
            System.out.println(token + "token");
            Optional<Person> personOptional = personRepository.findByToken(token);
            System.out.println(personOptional + "pers opt");
            if (personOptional.isEmpty()) {
                throw new BadCredentialsException("invalid token.");
            }
            Person person = personOptional.get();
            System.out.println(person + " person ");
            if (person.getToken() == null || (person.getTokenExpirationDate() != null && person.getTokenExpirationDate().before(new Date()))) {
                throw new BadCredentialsException("invalid token.");
            }
            System.out.println("role " + person.getRolesAsStrings());
            System.out.println("pered returnom");
            //TODO он не может найти роль, надо сделать роль как отделную таблицу
            System.out.println(new TokenAuthentication(person.getRolesAsStrings(), person.getToken(), person.getLogin(), person.getId()));
            return new TokenAuthentication(person.getRolesAsStrings(), person.getToken(), person.getLogin(), person.getId());
        } else {
            throw new BadCredentialsException("token is not provided");
        }
    }

}
