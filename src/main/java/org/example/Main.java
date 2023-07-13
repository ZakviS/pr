package org.example;


import org.example.Controller.ControllerEmployee;
import org.example.Entity.*;
//import org.example.Util.HibernateUtil;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.time.LocalDate;

//@SpringBootApplication
@SpringBootApplication
//@ComponentScan({"org.example.Controller","org.example.Entity","org.example.services","org.example.Repository"})
public class Main {

//    public static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args)  {

        SpringApplication.run(Main.class, args);
//
//        try (var sessionFactory = HibernateUtil.buildSessionFactory();
//             var session = sessionFactory.openSession()) {
//            session.beginTransaction();
//
//            session.getTransaction().commit();
//        }
//
//        ControllerEmployee controllerEmployee = new ControllerEmployee();

//        controllerEmployee.setName("nikita");
//        controllerEmployee.setSurname("ngrs");
//        controllerEmployee.setSecondSurname("qwdsc");
//        controllerEmployee.setBeginning(LocalDate.of(2000,8,11));
//        controllerEmployee.setDismissal(LocalDate.of(2050,8,11));
//        controllerEmployee.setPhoneNumber("123456181");
//        controllerEmployee.setEmail("nikita@gmail");
//        controllerEmployee.setSalary(2200L);
//        controllerEmployee.setDateSalary(LocalDate.of(2001,1,1));
//        controllerEmployee.setPosition("test");
//        controllerEmployee.saveEmployee();



    }

}