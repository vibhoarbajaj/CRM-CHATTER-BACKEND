package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Long chatId;
    private Long senderId;
    private String messageBody;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    //private String groupName ;

    @ManyToMany
    @JoinTable(name = "message_to_person",
            joinColumns = @JoinColumn(name = "received_message_id"),
            inverseJoinColumns = @JoinColumn(name = "receiver_person_id"))
    private List<Person> receivers;
}
