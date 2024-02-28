package com.example.demo.person;

import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class PersonApiResponse {
private String msg;
private Boolean success;
private HttpStatus status;
}
