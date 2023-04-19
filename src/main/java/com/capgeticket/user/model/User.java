package com.capgeticket.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    @NotEmpty(message = "El nombre es obligatorio")
    private String name;
    @Column(name = "lastname")
    @NotEmpty(message = "El apellido es obligatorio")
    private String lastname;
    @Column(name = "mail")
    @NotEmpty(message = "El email es obligatorio")
    private String mail;
    @Column(name = "password")
    @NotEmpty(message = "La contraseña es obligatoria")
    private String password;
    @Column(name = "date")
    @CreatedDate
    private String date;
}
