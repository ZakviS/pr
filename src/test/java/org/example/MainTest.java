package org.example;

import org.example.Entity.*;
import org.example.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.TreeMap;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.joining;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void checkManyToMany(){

        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();

//            Employee employee = Employee.builder()
//                    .name("alex")
//                    .surname("grin")
//                    .secondSurname("vick")
//                    .beginning(LocalDate.of(2015,10,10))
//                    .dismissal(LocalDate.of(2016,10,10))
//                    .build();


            var employee = session.get(Employee.class, 1L);

//            Salary salary = Salary.builder()
//                    .month(LocalDate.of(1,10,1))
//                    .sum(2000L)
//                    .build();
//            session.save(salary);

            var salary = session.get(Salary.class,5L);
            employee.addSalary(salary);

            //employee.getPositions().clear();

//            Position position = Position.builder()
//                    .name("test")
//                    .build();
            var position = session.get(Position.class,1L);
            employee.setPosition(position);

            session.saveOrUpdate(position);

            session.getTransaction().commit();
        }

    }

    @Test
    public void Employee(){
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();


            var employee = session.get(Employee.class, 1L);

//            Salary salary = Salary.builder()
//                    .month(LocalDate.of(1,10,1))
//                    .sum(2000.0)
//                    .build();
            var salary = session.get(Salary.class, 5L);
            employee.addSalary(salary);

//            employee.getPositions().clear();

//            var position = Position.builder()
//                    .name("test")
//                    .build();
            var position = session.get(Position.class,1L);
            employee.setPosition(position);
//            ProjectTeam projectTeam = ProjectTeam.builder()
//                    .startDate(LocalDate.of(2000,10,15))
//                    .endDate(LocalDate.of(2010,10,15))
//                    .staff(0.5)
//                    .build();
//            session.saveOrUpdate(projectTeam);

            var projectTeam = session.get(ProjectTeam.class,3L);
            employee.addProjectTeam(projectTeam);

            session.save(position);

            session.getTransaction().commit();
        }
    }

    @Test
    public void Project(){
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();

//            var projectType = session.get(ProjectType.class,10L);

//            Project project = Project.builder()
//                    .name("alex1")
//                    .start_date(LocalDate.of(2010,5,23))
//                    .end_date(LocalDate.of(2011,5,23))
//                    .sum(1500L)
//                    .projectType(projectType)
//                    .build();
//            session.saveOrUpdate(project);

            var project = session.get(Project.class,36L);

//            ProjectTeam projectTeam = ProjectTeam.builder()
//                    .staff(1)
//                    .startDate(LocalDate.of(2000,10,15))
//                    .endDate(LocalDate.of(2010,10,15))
//                    .project(project)
//                    .employee(employee)
//                    .build();

            //project.addProjectTeam(projectTeam);
            //projectType.addProject(project);

//            Revenue revenue = Revenue.builder()
//                    .datePayment(LocalDate.of(2005,10,10))
//                    .sum(150L)
//                    .build();

//            var revenue = session.get(Revenue.class,1L);
//            project.addRevenue(revenue);
//            session.saveOrUpdate(revenue);


            session.saveOrUpdate(project);

            session.getTransaction().commit();
        }
    }

    @Test
    public void Payment(){

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

//            PaymentGroup paymentGroup = PaymentGroup.builder()
//                    .name("testPG")
//                    .build();
//            session.saveOrUpdate(paymentGroup);
//            PaymentType paymentType = PaymentType.builder()
//                    .name("testPT")
//                    .build();
//            session.saveOrUpdate(paymentType);
//            Payment payment = Payment.builder()
//                    .name("abc")
//                    .sum(100L)
//                    .year(LocalDate.of(1000,10,10))
//                    .month(LocalDate.of(1000,10,10))
//                    .paymentGroup(paymentGroup)
//                    .paymentType(paymentType)
//                    .build();

            var payment = session.get(Payment.class,2L);


            session.saveOrUpdate(payment);
            var revenue = session.get(Revenue.class,1L);
            payment.addRevenue(revenue);
            session.saveOrUpdate(payment);

            session.getTransaction().commit();

        }

    }

    @Test
    public void test(){//not work
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            var payment = session.get("abc","abc");


            session.saveOrUpdate(payment);
            var revenue = session.get(Revenue.class,1L);
      //      payment.addRevenue(revenue);
            session.saveOrUpdate(payment);

            session.getTransaction().commit();

        }
    }

    @Test
    void checkReflectionApi() throws IllegalAccessException, SQLException {
        Project project = Project.builder()
                .name("alex")
                .start_date(LocalDate.of(2010,5,23))
                .end_date(LocalDate.of(2011,5,23))
                .sum(1500L)
                .build();
        String sql = """
                insert
                into
                %s
                (%s)
                values
                (%s)
                """;
        String tableName = ofNullable(project.getClass().getAnnotation(Table.class))
                .map(tableAnnotation -> tableAnnotation.schema() + "." + tableAnnotation.name())
                .orElse(project.getClass().getName());

        Field[] declaredFields = project.getClass().getDeclaredFields();
        String columnNames = Arrays.stream(declaredFields)
                .map(field -> ofNullable(field.getAnnotation(Column.class))
                        .map(Column::name)
                        .orElse(field.getName()))
                .collect(joining(", "));

        String columnValues = Arrays.stream(declaredFields)
                .map(field -> "?")
                .collect(joining(", "));

        System.out.println(sql.formatted(tableName, columnNames, columnValues));

        Connection connection = null;
        PreparedStatement preparedStatement = connection.prepareStatement(sql.formatted(tableName, columnNames, columnValues));
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            preparedStatement.setObject(1, declaredField.get(project));


        }
    }
}