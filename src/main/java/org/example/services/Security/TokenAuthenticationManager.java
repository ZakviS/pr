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
            String token = ((TokenAuthentication) authentication).getToken();
            Optional<Person> personOptional = personRepository.findOneByToken(token);
            if (personOptional.isEmpty()) {
                throw new BadCredentialsException("invalid token.");
            }
            Person person = personOptional.get();
            if (person.getToken() == null || (person.getTokenExpirationDate() != null && person.getTokenExpirationDate().before(new Date()))) {
                throw new BadCredentialsException("invalid token.");
            }
            return new TokenAuthentication(person.getRolesAsStrings(), person.getToken(), person.getLogin(), person.getId());
        } else {
            throw new BadCredentialsException("token is not provided");
        }
    }

}
