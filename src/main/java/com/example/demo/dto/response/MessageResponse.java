package com.example.demo.dto.response;

import com.example.demo.model.Person;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MessageResponse {
    private Long Id;
    private Long chatId;
    private Long senderId;
    private String messageBody;
    private List<Person> receivers;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
