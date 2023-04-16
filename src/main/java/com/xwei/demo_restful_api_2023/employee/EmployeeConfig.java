package com.xwei.demo_restful_api_2023.employee;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class EmployeeConfig {

    @Bean
    CommandLineRunner commandLineRunner(EmployeeRepository repository) {
        return args -> {
            Employee anna = new Employee(
                    "Anna",
                    "Anna@gmail.com",
                    LocalDate.of(1990, Month.JANUARY, 1));

            Employee becky = new Employee(
                    "Becky",
                    "Becky@gmail.com",
                    LocalDate.of(2000, Month.FEBRUARY, 2));

            repository.saveAll(
                    List.of(anna, becky)
            );
        };
    }
}
