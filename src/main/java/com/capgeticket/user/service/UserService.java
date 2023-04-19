package com.capgeticket.user.service;

import com.capgeticket.user.model.User;
import com.capgeticket.user.response.UserResponse;
import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<UserResponse> addUser(User user);

    List<UserResponse> getUsers();

    boolean deleteUser(Long id);

    UserResponse modifyUser(User user);

    Optional<UserResponse> getById(String id);
}
