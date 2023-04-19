package com.capgeticket.user;

import com.capgeticket.user.model.User;
import com.capgeticket.user.repository.UserRepository;
import com.capgeticket.user.service.UserService;
import com.capgeticket.user.service.UserServiceImpl;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;

@WebMvcTest(UserServiceImpl.class)
@Slf4j
public class UserServiceTest {

    @Autowired
    private UserService service;
    @MockBean
    private UserRepository repository;

    @Test
    public void addUserTest_OK() {
        User user = new User(10L, "laura", "lopez", "laulop@", "hshhshs", "12-12-12");
        Mockito.when(repository.existsByMail("")).thenReturn(false);
        Mockito.when(repository.save(user)).thenReturn(user);
        assertThat(service.addUser(user)).isEqualTo(Optional.of(user));
    }

    @Test
    public void addUserTest_AlreadyExists() {
        User user = new User(10L, "laura", "lopez", "laulop@", "hshhshs", "12-12-12");
        Mockito.when(repository.existsByMail(user.getMail())).thenReturn(true);
        assertThat(service.addUser(user)).isEqualTo(Optional.empty());
    }
}
