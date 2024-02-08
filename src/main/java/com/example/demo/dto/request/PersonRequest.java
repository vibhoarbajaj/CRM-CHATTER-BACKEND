package com.example.demo.dto.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PersonRequest {
    private String name;
    private String userName;
    private String email;
    private String phone;
}