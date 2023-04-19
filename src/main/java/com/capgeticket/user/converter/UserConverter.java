package com.capgeticket.user.converter;

import com.capgeticket.user.model.User;
import com.capgeticket.user.response.UserResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

/*
 * Se utiliza para convertir de entity a DTO
 */
@Component
public class UserConverter {

    public UserResponse of(User user) {

        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .lastname(user.getLastname())
                .mail(user.getMail())
                .date(user.getDate())
                .password(user.getPassword())
                .build();
    }

    public List<UserResponse> of(List<User> users) {
        return users.stream().map(p -> of(p)).collect(Collectors.toList());
    }

}
