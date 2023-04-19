package com.capgeticket.user;

import com.capgeticket.user.converter.UserConverter;
import com.capgeticket.user.model.User;
import com.capgeticket.user.response.UserResponse;
import com.capgeticket.user.service.UserService;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserConverter converter;

    @MockBean
    private UserService service;


    @Test
    void createUser() throws Exception {

        long id = 1L;
        User user = new User();
        //User user =new User(id, "nameTest", "lastnameTest", "emailTes", "04/04/2023", "PasswordTest");
        //user.setId(id);
        user.setName("nameTest");
        user.setLastname("lastnameTest");
        user.setMail("emailTest7");
        user.setDate("04/04/2023");
        user.setPassword("PasswordTest");

        UserResponse userResponse = UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .lastname(user.getLastname())
                .mail(user.getMail())
                .date(user.getDate())
                .password(user.getPassword())
                .build();

        when(service.addUser(user)).thenReturn(Optional.of(userResponse));
        //assertThat(controller.addUser(user)).isEqualTo(ResponseEntity.ok().body(converter.of(user)));

    }


}
