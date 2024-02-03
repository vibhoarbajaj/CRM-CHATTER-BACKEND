package com.example.demo.dto.request;

import lombok.*;

@Data
public class CustomerRequest {
    private String cust_name;

    public CustomerRequest(String cust_name) {
        this.cust_name = cust_name;
    }

    public CustomerRequest() {
    }

    public String getCust_name() {
        return cust_name;
    }

    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
    }
}
