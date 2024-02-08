package com.example.demo.dto.request;

import lombok.Data;

import java.util.Set;

@Data
public class ChatRequest {
    private Set<Long> personIds;
    private String name;
    private Boolean isGroup;

    public ChatRequest(Set<Long> personIds, String name, Boolean isGroup) {
        this.personIds = personIds;
        this.name = name;
        this.isGroup = isGroup;
    }

    public ChatRequest() {
    }

    public Set<Long> getPersonIds() {
        return personIds;
    }

    public void setPersonIds(Set<Long> personIds) {
        this.personIds = personIds;
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

}
