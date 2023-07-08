package org.example;


import org.example.Entity.Employee;
import org.example.Entity.Project;
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

        log.info("try to create sessionFactory");

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            log.info(" create sessionFactory");

            session.beginTransaction();
            log.info("start transaction");

            Project project = Project.builder()
                    .name("alex")
                    .start_date(LocalDate.of(2010,5,23))
                    .end_date(LocalDate.of(2011,5,23))
                    .sum(1500)
                    .build();
            log.info("Project entity is in transaction, object: {}",project);

            session.save(project);
            log.info("Project is save: {}, session{}",project,session);

        } catch (Exception e ){
            log.error("Exception", e);
            throw e;
        }
    }
}