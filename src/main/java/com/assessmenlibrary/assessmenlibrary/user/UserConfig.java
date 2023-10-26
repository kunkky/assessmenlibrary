package com.assessmenlibrary.assessmenlibrary.user;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class UserConfig {
    //bring in the password encoder to encode password before saving to db
    private final PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository){
        return args ->{
            User kunkky=	new User(1,
                    "thomas",
                    "kunkkykukky@gmail.com",
                    "ademuyiwa",
                    "Adekunle",
                    "Kunkky",
                    Role.ADMIN
            );
            kunkky.setPassword(passwordEncoder.encode("password"));
            User kunle=	new User(
                    2,
                    "kunle",
                    "user@gmail.com",
                    "ademuyiwa",
                    "user",
                    "thomas",
                    Role.USER
            );
            kunle.setPassword(passwordEncoder.encode("password"));
            repository.saveAll(
                    List.of(kunkky, kunle)

            );

        };
    }

}
