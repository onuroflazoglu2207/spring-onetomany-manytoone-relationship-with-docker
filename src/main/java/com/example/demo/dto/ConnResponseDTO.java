package com.example.demo.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConnResponseDTO {
    Long identity;
    String username;
    String password;
    Long parent;
}
