package edu.quattrinh.webservice.configuration;

import edu.quattrinh.webservice.entity.User;
import edu.quattrinh.webservice.enums.Role;
import edu.quattrinh.webservice.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner() {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
//                Set<String> roles = new HashSet<>();
//                roles.add(Role.ADMIN.name());

                User user = User.builder()
                        .username("admin")
                        //.roles(roles)
                        .password(passwordEncoder.encode("admin"))
                        .build();

                userRepository.save(user);
                log.warn("admin user has been created with default password ");

            }
        };
    }
}
