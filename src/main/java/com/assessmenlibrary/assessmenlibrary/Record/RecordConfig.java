package com.assessmenlibrary.assessmenlibrary.Record;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class RecordConfig {
    @Bean
    CommandLineRunner recordCommandLineRunner(RecordRepository repository){
        return args ->{
            Record fakeRecord=	new Record(1,
                    "Kunkky",
                    LocalDate.of(2022, Month.FEBRUARY,2),
                    "eat that frog",
                    1,
                    "not Returned"
            );
            Record secondFakeRecord=	new Record(2,
                    "John Doe",
                    LocalDate.of(2022, Month.FEBRUARY,2),
                    "eat that frog",
                    1,
                    "not Returned"
            );
            repository.saveAll(
                    List.of(fakeRecord, secondFakeRecord)

            );
        };
    }
}
