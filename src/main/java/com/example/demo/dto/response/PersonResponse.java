package com.example.demo.dto.response;

import lombok.*;

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

}