package org.example;

import org.example.Entity.Employee;
import org.example.Entity.Position;
import org.example.Entity.Project;
import org.example.Entity.Salary;
import org.example.Util.HibernateUtil;
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

            Salary salary = Salary.builder()
                    .month(LocalDate.of(1,10,1))
                    .sum(2000L)
                    .build();
            employee.addSalary(salary);

            employee.getPositions().clear();

            var position = Position.builder()
                    .name("test")
                    .build();
            employee.addPosition(position);

            session.save(position);

            session.getTransaction().commit();
        }

    }




    @Test
    void checkReflectionApi() throws IllegalAccessException, SQLException {
        Project project = Project.builder()
                .name("alex")
                .start_date(LocalDate.of(2010,5,23))
                .end_date(LocalDate.of(2011,5,23))
                .sum(1500)
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