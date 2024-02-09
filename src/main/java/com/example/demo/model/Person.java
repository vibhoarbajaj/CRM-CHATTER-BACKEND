package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id
    @Column(name = "person_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_sequence")
    @SequenceGenerator(name = "person_sequence", sequenceName = "person_sequence", allocationSize = 1)
    private Long id;
    private String name;
    private String userName;
    private String email;
    private String phone;
    private LocalDateTime createdAt;

    @JsonIgnore
    @ManyToMany(mappedBy = "personSet")
    private Set<Chat> chatSet = new HashSet<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "receivers", fetch = FetchType.EAGER)
    private List<Message> messageList;
}