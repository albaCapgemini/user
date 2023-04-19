package com.capgeticket.user.service;

import com.capgeticket.user.converter.UserConverter;
import com.capgeticket.user.errors.BadRequestException;
import com.capgeticket.user.model.User;
import com.capgeticket.user.repository.UserRepository;
import com.capgeticket.user.response.UserResponse;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserConverter converter;

    /**
     * Introducir un nuevo usuario a la base de datos
     *
     * @param user : el usuario que se quiere introducir
     * @return el usuario que se introduce o vacio si ya existia
     */
    @Override
    public Optional<UserResponse> addUser(User user) {
        return repository.existsByMail(user.getMail()) ? Optional.empty() : Optional.of(converter.of(repository.save(user)));
    }

    @Override
    public List<UserResponse> getUsers() {
        return converter.of(repository.findAll());
    }

    @Override
    public boolean deleteUser(Long id) {
        if (id == null) {
            return false;
        }
        repository.deleteById(Long.valueOf(id));
        return true;
    }

    @Override
    public UserResponse modifyUser(User user) {
        return repository.existsById(user.getId()) ? converter.of(repository.save(user)) : null;
    }

    @Override
    public Optional<UserResponse> getById(String id) {
        if (id == null || id.isBlank()) {
            throw new BadRequestException();
        }
        return repository.findById(Long.valueOf(id))
                .map(user -> converter.of(user));
    }
}
