package com.capgeticket.user.controller;

import com.capgeticket.user.errors.BadRequestException;
import com.capgeticket.user.model.User;
import com.capgeticket.user.response.UserResponse;
import com.capgeticket.user.service.UserService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    /**
     * Guarda un nuevo usuario, validando que los datos que se introducen no están vacios o null
     *
     * @param user usuario que se quiere guardar, validando no esta vacio o null
     * @return un ResponseEntity con el usuario que se ha guardado o error si algo fue mal
     */
    @PostMapping
    public ResponseEntity<UserResponse> addUser(@Valid @RequestBody User user) {
        var response = service.addUser(user);
        if (response.isEmpty()) {
            throw new BadRequestException(user.getMail());
        }
        return ResponseEntity.ok(response.get());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getById(@PathVariable("userId") String userId){
        var response = service.getById(userId);
        return response.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers (){
        var response = service.getUsers();
        return response.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(response);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") Long userId){
        var response = service.deleteUser(userId);
        return response ? ResponseEntity.ok("Se ha eliminado correctamente") : ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<UserResponse> modifUser(@RequestBody User user){
        var response = service.modifyUser(user);
        return response != null ? ResponseEntity.ok(response) : ResponseEntity.badRequest().build();
    }
}
