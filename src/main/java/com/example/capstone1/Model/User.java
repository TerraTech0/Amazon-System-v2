package com.example.capstone1.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "User")
public class User {

    @NotNull(message = "id can't be empty!")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "username can't be empty!")
    @Size(min = 5, message = "username has to be more than 5 length long!")
    @Column(columnDefinition = "varchar(15) not null")
    private String username;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]+$", message = "please ensure that the input of password has at least one character and one digit!")
    @NotEmpty(message = "password can't be empty!")
    @Column(columnDefinition = "varchar(255) not null")
    private String password;

    @NotEmpty(message = "email can't be empty!")
    @Email
    @Column(columnDefinition = "varchar(50) NOT NULL UNIQUE")
    private String email;

    @NotEmpty(message = "role can't be empty!")
    @Pattern(regexp = "^(Admin|Customer)$")
    @Column(columnDefinition = "varchar(10) not null check(role = 'Admin' or role='Customer')")
    private String role;

    @NotNull(message = "balance can't be null!")
    @Positive(message = "balance must be poisitve!")
    @Column(columnDefinition = "double not null default 0")
    private Double balance;


}
