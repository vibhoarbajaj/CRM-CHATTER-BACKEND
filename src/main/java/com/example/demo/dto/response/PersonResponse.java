package com.example.demo.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonResponse {
    private Long id;
    private String name;
    private String userName;
    private String email;
    private String phone;
    private LocalDateTime createdAt;
}