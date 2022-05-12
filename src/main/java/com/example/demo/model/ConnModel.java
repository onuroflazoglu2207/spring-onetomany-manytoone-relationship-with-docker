package com.example.demo.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "conns")
public class ConnModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long identity;

    @Email(message = "Unacceptable email address!")
    String username;

    @NotBlank(message = "Password cannot be blank!")
    String password;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    UserModel user;
}
