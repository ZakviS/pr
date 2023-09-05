package org.example.services.Security;

import org.example.Entity.Person;
import org.example.Model.PersonModel;
import org.example.Repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;

@Service
public class PersonService {

    @Autowired
    private PersonRepo personRepo;
//
//    @Transactional
//    public Person getPersonSelf() {
//        String login = SecurityContextHolderUtils.getCurrentUserLogin();
//        if (login == null) {
//            throw new NotAuthorizedException("Вход не выполнен.");
//        }
//        Person person = personRepo.findOneByLogin(login).get();
//        return person;
//    }
//
//    @Transactional
//    public PersonModel getPersonSelfDto() {
//        String login = SecurityContextHolderUtils.getCurrentUserLogin();
//        if (login == null) {
//            throw new NotAuthorizedException("Вход не выполнен.");
//        }
//        Person person = personRepo.findOneByLogin(login).get();
//        int passwordValidTermInDays = authConfigService.getConfig().getValidTermInDays();
//        return mapToPersonSelfDto(person, passwordValidTermInDays);
//    }

    public PersonModel mapToPersonDto(Person entity, int passwordValidTermInDays) {
        PersonModel dto = new PersonModel();
        dto.setId(entity.getId());
        dto.setLastName(entity.getLastName());
        dto.setFirstName(entity.getFirstName());
        dto.setMiddleName(entity.getMiddleName());
        dto.setLogin(entity.getLogin());
        dto.setPasswordExpired(isPasswordExpired(entity, passwordValidTermInDays));
        dto.setRoles(new LinkedHashSet<>(entity.getRoles()));
        //
        dto.setToken(entity.getToken());
        dto.setFullName(entity.getFullName());

        return dto;
    }

    private boolean isPasswordExpired(Person person, int passwordValidTermInDays) {
        Calendar calendar = Calendar.getInstance();
        if (person.getLastPassChange() == null) {
            return true;
        }
        calendar.setTime(person.getLastPassChange());
        calendar.add(Calendar.DAY_OF_MONTH, passwordValidTermInDays);
        return calendar.getTime().before(new Date());
    }

}
