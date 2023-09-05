package org.example.services.Security;


import org.example.Entity.Person;
import org.example.Model.PersonModel;
import org.example.Model.RegisterModel;
import org.example.Model.SignInRequest;
import org.example.Model.SignInResponse;
import org.example.Repository.PersonRepo;
import org.example.Util.SecurityContextHolderUtils;
import org.hibernate.boot.model.internal.XMLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.token.DefaultToken;
import org.springframework.security.core.token.TokenService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.AccountException;
import javax.security.auth.login.AccountLockedException;
import javax.security.auth.login.AccountNotFoundException;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private PersonRepo personRepo;
    @Autowired
    private PasswordService passwordService;
    @Autowired
    private PersonService personService;

    private TokenService tokenService;
    @Value("${auth.token-expire-minutes}")
    private int tokenExpireMinutes;


    @Transactional
    public SignInResponse signIn(SignInRequest request) throws AccountException {
        String login = request.getLogin();
        System.out.println(login);
        String password = request.getPassword();
        System.out.println(password);
        System.out.println(personRepo.findAll() + " find all");
        try {
            Optional<Person> personByLogin = personRepo.findOneByLogin(login);
            if (personByLogin.isEmpty()) {
                throw new AccountNotFoundException("Пользователь с указанным логином не найден.");
            }
            Person person = personByLogin.get();
            if (person.getToken() == null){
                System.out.println("token is null");

            }
            if (Boolean.TRUE.equals(person.getBlocked())) {
                throw new AccountLockedException("Учетная запись заблокирована");
            }
            if (!passwordService.getPasswordEncoder().matches(password, person.getPassword())) {
                person.setAuthFails(person.getAuthFails() + 1);
//                if (person.getAuthFails() >= authConfigService.getConfig().getAuthCount()) {
//                    person.setBlocked(true);
//                }
                personRepo.save(person);
                throw new AccountException("Не удается войти.\nПожалуйста, проверьте правильность написания логина и пароля.");
            }
            person.setAuthFails(0);
            String token="123";
            System.out.println(token);
            if (person.getToken() == null || (person.getTokenExpirationDate() != null && person.getTokenExpirationDate().before(new Date()))) {
                System.out.println("idas");
                token = tokenService.allocateToken(login).getKey();
                System.out.println(tokenService.allocateToken(login));
                System.out.println(tokenService.allocateToken(login).getKey());
                System.out.println(token);
                person.setToken(token);
                Calendar calendar = Calendar.getInstance();
                person.setTokenCreatedDate(calendar.getTime());
                calendar.add(Calendar.MINUTE, tokenExpireMinutes);
                person.setTokenExpirationDate(calendar.getTime());
            } else {
                token = person.getToken();
            }
            // for audit
//            SecurityContextHolderUtils.setAuthentication(new TokenAuthentication(person.getRoles().stream().map(v -> v.name()).collect(Collectors.toList()), person.getToken(), person.getLogin(), person.getId()));
//            authAuditService.write(AuthAuditRecordType.AUTH, "Успешная аутентификация", null);
//            int passwordValidTermInDays = authConfigService.getConfig().getValidTermInDays();
            // TODO mapToPersonSelfDto depends on SecurityContextHolder so we can call it only after setAuthentication
            PersonModel dto = personService.mapToPersonDto(person, 900);//второе значение это дни валидности пароля
            dto.setToken(token);
            System.out.println(dto);
            return new SignInResponse(dto);
        } catch (Exception e) {
//            authAuditService.write(AuthAuditRecordType.AUTH_ERROR, e.getMessage(), null);
            throw e;
        }
    }

    @Transactional
    public void signOut() {
        String login = SecurityContextHolderUtils.getCurrentUserLogin();
        try {
            Optional<Person> personOptional = personRepo.findOneByLogin(login);
            Person person = personOptional.get();
            person.setToken(null);
            person.setTokenCreatedDate(null);
            person.setTokenExpirationDate(null);
//            authAuditService.write(AuthAuditRecordType.AUTH, "Пользователь вышел из системы", null);
        } catch (Exception e) {
//            authAuditService.write(AuthAuditRecordType.AUTH_ERROR, e.getMessage(), null);
        }
    }

    @Transactional
    public void register(RegisterModel request) throws AccountException {
        System.out.println("register");
        String login = request.getLogin();
        String password = request.getPassword();
        String crypto = passwordService.getPasswordEncoder().encode(password);
        System.out.println(crypto);
        try {
            System.out.println(login + " " + password);
            System.out.println();
        } catch (Exception e) {
//            authAuditService.write(AuthAuditRecordType.AUTH_ERROR, e.getMessage(), null);
            throw e;
        }
    }

    @Transactional
    public void keepalive() {
        String login = SecurityContextHolderUtils.getCurrentUserLogin();
        Person person = personRepo.findOneByLogin(login).get();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, tokenExpireMinutes);
        person.setTokenExpirationDate(calendar.getTime());
    }

}
