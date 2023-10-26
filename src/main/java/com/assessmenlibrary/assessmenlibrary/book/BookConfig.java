package com.assessmenlibrary.assessmenlibrary.book;

import java.time.LocalDate;
import java.time.Month;

import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookConfig {
    @Bean
    CommandLineRunner userCommandLineRunner(BookRepository repository){
        return args ->{
            		Book frogBook=	new Book(
                    "eat that frog",
                    "Brian Tracy",
                    "Scifi",
                    "available",
                3,
                    LocalDate.of(2022,Month.FEBRUARY,2)
                            );
                Book babylon=	new Book(
                    "Richest Man in Babylon",
                    "George S. Clason",
                    "Scifi",
                    "available",
                3,
                    LocalDate.of(2022,Month.DECEMBER,2)
                            );
                    repository.saveAll(
                                        List.of(frogBook, babylon)

                    );
        };
    }
}
