package edu.quattrinh.webservice.service;

import java.time.LocalDate;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;

import edu.quattrinh.webservice.dto.request.UserCreationRequest;
import edu.quattrinh.webservice.dto.response.UserResponse;
import edu.quattrinh.webservice.entity.User;
import edu.quattrinh.webservice.exception.AppException;
import edu.quattrinh.webservice.repository.UserRepository;

@SpringBootTest
@TestPropertySource("/test.properties")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    private UserCreationRequest request;
    private UserResponse response;
    private LocalDate dob;
    private User user;

    @BeforeEach
    private void initData() {

        dob = LocalDate.of(1999, 11, 25);

        request = UserCreationRequest.builder()
                .username("quattrinh")
                .firstName("Quat")
                .lastName("Trinh")
                .password("12345678")
                .dob(dob)
                .build();

        //        response = UserResponse.builder()
        //                .id("sagagagwegwegwe")
        //                .username("quattrinh")
        //                .firstName("Quat")
        //                .lastName("Trinh")
        //                .dob(dob)
        //                .build();

        user = User.builder()
                .id("sagagagwegwegwe")
                .username("quattrinh")
                .firstName("Quat")
                .lastName("Trinh")
                .dob(dob)
                .build();
    }

    @Test
    void createUser_validRequest_success() {
        // GIVEN
        Mockito.when(userRepository.existsByUsername(Mockito.anyString())).thenReturn(false);
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);

        // WHEN
        var response = userService.createUser(request);

        // THEN
        Assertions.assertThat(response.getId()).isEqualTo("sagagagwegwegwe");
        Assertions.assertThat(response.getUsername()).isEqualTo("quattrinh");
    }

    @Test
    void createUser_userExisted_fail() {
        // GIVEN
        Mockito.when(userRepository.existsByUsername(Mockito.anyString())).thenReturn(true);

        // WHEN
        var exception = org.junit.jupiter.api.Assertions.assertThrows(
                AppException.class, () -> userService.createUser(request));

        // THEN
        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1002);
    }

    @Test
    @WithMockUser(username = "quattrinh")
    void getMyInfo_valid_success() {
        // GIVEN
        Mockito.when(userRepository.findByUsername(Mockito.anyString())).thenReturn(Optional.of(user));

        // WHEN
        var response = userService.getMyInfo();

        // THEN
        Assertions.assertThat(response.getUsername()).isEqualTo("quattrinh");
        Assertions.assertThat(response.getId()).isEqualTo("sagagagwegwegwe");
    }

    @Test
    @WithMockUser(username = "quattrinh")
    void getMyInfo_userNotFound_error() {
        // GIVEN
        Mockito.when(userRepository.findByUsername(Mockito.anyString())).thenReturn(Optional.ofNullable(null));

        // WHEN
        var exception =
                org.junit.jupiter.api.Assertions.assertThrows(AppException.class, () -> userService.getMyInfo());

        // THEN
        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1005);
    }
}
