package com.example.demo.dto.request;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MessageRequest {
    private Long chatId;
    private Long senderId;
    private String senderName;
    private String messageBody;
    private List<Long> receiversId;
}
