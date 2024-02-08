package com.example.demo.dto.response;

import com.example.demo.model.Person;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class ChatResponse {
    private Long id;
    private Set<Person> personSet;
    private String name;
    private Boolean isGroup;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ChatResponse(Long id, Set<Person> personSet, String name, Boolean isGroup, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.personSet = personSet;
        this.name = name;
        this.isGroup = isGroup;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public ChatResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        this.isGroup = group;
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
}
