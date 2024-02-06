package com.example.demo.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table
public class Chat {
    @Id
    @Column(name="chat_id",unique = true,nullable = false)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "chat_sequence")
    @SequenceGenerator(name = "chat_sequence", sequenceName = "chat_sequence",allocationSize = 1)
    private String id;
    private Set<Person> personSet=new HashSet<>();
    private String name;
    private  Boolean isGroup;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Chat(String id, Set<Person> personSet, String name, Boolean isGroup, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.personSet = personSet;
        this.name = name;
        this.isGroup = isGroup;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Chat() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<Person> getPersonSet() {
        return personSet;
    }

    public void setPersonSet(Set<Person> personSet) {
        this.personSet = personSet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getGroup() {
        return isGroup;
    }

    public void setGroup(Boolean group) {
        isGroup = group;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "id='" + id + '\'' +
                ", personSet=" + personSet +
                ", name='" + name + '\'' +
                ", isGroup=" + isGroup +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
