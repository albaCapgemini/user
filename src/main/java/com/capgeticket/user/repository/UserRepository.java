package com.capgeticket.user.repository;

import com.capgeticket.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Comprueba si existe algun usuario con ese email
     *
     * @param email : email por el que se va a buscar
     * @return true si ya existe o false si no
     */
    boolean existsByMail(String email);
}
