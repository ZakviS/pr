package org.example.services;


import org.example.Entity.Person;
import org.example.Model.SignInRequest;
import org.example.Model.SignInResponse;
import org.example.Repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.AccountException;
import javax.security.auth.login.AccountExpiredException;
import javax.security.auth.login.AccountLockedException;
import javax.security.auth.login.AccountNotFoundException;
import java.nio.channels.AcceptPendingException;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthService {

    @Autowired
    private PersonRepo personRepo;
    @Autowired
    private PasswordService passwordService;


    @Transactional
    public SignInResponse signIn(SignInRequest request) {
        String login = request.getLogin();
        String password = request.getPassword();
        try {
            Optional<Person> personByLogin = personRepo.findOneByLogin(login);
            if (personByLogin.isEmpty()) {
                throw new AccountNotFoundException("Пользователь с указанным логином не найден.");
            }
            Person person = personByLogin.get();
            if (Boolean.TRUE.equals(person.getBlocked())) {
                throw new AccountLockedException("Учетная запись заблокирована");
            }
            if (!passwordService.getPasswordEncoder().matches(password, person.getPassword())) {
                person.setAuthFails(person.getAuthFails() + 1);
                if (person.getAuthFails() >= authConfigService.getConfig().getAuthCount()) {
                    person.setBlocked(true);
                }
                personRepo.save(person);
                throw new AccountException("Не удается войти.\nПожалуйста, проверьте правильность написания логина и пароля.");
            }
            person.setAuthFails(0);
            String token;
            if (person.getToken() == null || (person.getTokenExpirationDate() != null && person.getTokenExpirationDate().before(new Date()))) {
                token = tokenService.allocateToken(login).getKey();
                person.setToken(token);
                Calendar calendar = Calendar.getInstance();
                person.setTokenCreatedDate(calendar.getTime());
                calendar.add(Calendar.MINUTE, tokenExpireMinutes);
                person.setTokenExpirationDate(calendar.getTime());
            } else {
                token = person.getToken();
            }
            // for audit
            SecurityContextHolderUtils.setAuthentication(new TokenAuthentication(person.getRoles().stream().map(v -> v.name()).collect(Collectors.toList()), person.getToken(), person.getLogin(), person.getId()));
            authAuditService.write(AuthAuditRecordType.AUTH, "Успешная аутентификация", null);
            int passwordValidTermInDays = authConfigService.getConfig().getValidTermInDays();
            // TODO mapToPersonSelfDto depends on SecurityContextHolder so we can call it only after setAuthentication
            PersonSelfDto dto = personSelfService.mapToPersonSelfDto(person, passwordValidTermInDays);
            dto.setToken(token);
            return new SignInResponse(dto);
        } catch (Exception e) {
            authAuditService.write(AuthAuditRecordType.AUTH_ERROR, e.getMessage(), null);
            throw e;
        }




        return null;
    }
}
