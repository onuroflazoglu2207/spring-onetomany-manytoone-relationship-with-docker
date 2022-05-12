package com.example.demo.dto;

import com.example.demo.model.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequestDTO {
    String name;
    LocalDate birthday;
    Gender gender;
    List<ConnResponseDTO> conns;
}
