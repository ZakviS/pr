package org.example;


import org.example.Controller.ControllerEmployee;
import org.example.Entity.*;
import org.example.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.time.LocalDate;

public class Main {

    public static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args)  {

        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();

            session.getTransaction().commit();
        }

        ControllerEmployee controllerEmployee = new ControllerEmployee();

        controllerEmployee.setName("nikita");
        controllerEmployee.setSurname("ngrs");
        controllerEmployee.setSecondSurname("qwdsc");
        controllerEmployee.setBeginning(LocalDate.of(2000,8,11));
        controllerEmployee.setDismissal(LocalDate.of(2050,8,11));
        controllerEmployee.setPhoneNumber("123456181");
        controllerEmployee.setEmail("nikita@gmail");
        controllerEmployee.setSalary(2200L);
        controllerEmployee.setDateSalary(LocalDate.of(2001,1,1));
        controllerEmployee.setPosition("test");
        controllerEmployee.saveEmployee();



    }

}