package com.example.demo.dto.request;

import lombok.Data;

@Data
public class CustomerGroupRequest {
    private String group_name;

    public CustomerGroupRequest() {
    }
    public CustomerGroupRequest(String group_name) {
        this.group_name = group_name;
    }
    @Override
    public String toString() {
        return "CustomerGroupRequest{" +
                "group_name='" + group_name + '\'' +
                '}';
    }

    public String getCustomer_group_name() {
        return group_name;
    }

    public void setCustomer_group_name(String group_name) {
        this.group_name = group_name;
    }


}
