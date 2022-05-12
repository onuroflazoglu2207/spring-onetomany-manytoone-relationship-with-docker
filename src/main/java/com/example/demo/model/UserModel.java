package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "users")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long identity;

    @NotBlank(message = "Name cannot be blank!")
    String name;

    @NotNull(message = "Birthday cannot be null!")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = ISO.DATE)
    LocalDate birthday;

    @NotNull(message = "Gender cannot be null!")
    @Enumerated(EnumType.STRING)
    Gender gender;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<ConnModel> conns;
}

