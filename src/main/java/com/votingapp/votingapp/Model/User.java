package com.votingapp.votingapp.Model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;

    @Column(name = "username", nullable = false)
    @NotBlank(message = "*Please provide your username")
    private String username;

    @Column(name = "password", nullable = false,length = 60)
    @NotBlank(message = "*Please provide your password")
    private String password;

    @Column(name = "email", unique = true, nullable = false)
    @Email(message = "*Please provide a valid Email")
    @NotBlank(message = "*Please provide an email")
    private String email;

    @Column(name = "phone")
    @Pattern(regexp = "^[1-9][0-9]{9}$",message = "*Please provide a valid Phone Number")
    @NotBlank(message = "*Please provide a Phone Number")
    private String phoneno;

    @Column(name = "role")
    private String role;

    @ManyToOne(cascade = CascadeType.ALL)
    private Vote vote;

}
