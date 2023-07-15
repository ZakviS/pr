package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Builder;
import org.example.Entity.*;
import org.example.Repository.PositionRepo;
import org.example.Repository.SalaryRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;

class MainTest {


    ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();


    @Test
    void pojoToJson() throws JsonProcessingException {




        objectMapper.registerModule(new JavaTimeModule());

        Position positions = Position.builder()
                .name("abc").build();

        Salary salary = Salary.builder()
                .sum(800L)
                .month(LocalDate.of(2005,10,20))
                .dateOfOrder(LocalDate.of(2007,10,20))
                .numberOfOrder(4851357L)
                .build();

        Employee employee = Employee.builder()
                .name("alex")
                .surname("grin")
                .secondSurname("vick")
                .beginning(LocalDate.of(2000,10,20))
                .dismissal(LocalDate.of(2010,10,20))
                .email("alex@gmail")
                .phoneNumber("88005553535")
                .position(positions)
                .build();

        ProjectTeam projectTeam = ProjectTeam.builder()
                .startDate(LocalDate.of(2005,10,20))
                .build();


        String json = objectMapper.writeValueAsString(salary);

        System.out.println(json);
    }

    @Test
    void jsonStringToPojo() throws JsonProcessingException {
        //objectMapper.registerModule(new JavaTimeModule());
        String employeeJson = "{\n" +
                " \"name\" : \"Jalil\",\n" +
                "  \"surname\" : \"Jarjanazy\",\n" +
                "  \"beginning\" : [2000,10,20]\n" +
                "}";

        Employee employee = objectMapper.readValue(employeeJson, Employee.class);

        System.out.println(employee);
    }
}