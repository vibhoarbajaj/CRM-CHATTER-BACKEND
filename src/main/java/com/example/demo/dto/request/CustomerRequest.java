package com.example.demo.dto.request;

import com.example.demo.model.CustomerGroup;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class CustomerRequest {
    private String cust_name;
    private Set<CustomerGroup> customerGroupList=new HashSet<>();



    public CustomerRequest(String cust_name, Set<CustomerGroup> customerGroupList) {
        this.cust_name = cust_name;
        this.customerGroupList = customerGroupList;
    }

    public CustomerRequest() {
    }

    public Set<CustomerGroup> getCustomerGroupList() {
        return customerGroupList;
    }

    public void setCustomerGroupList(Set<CustomerGroup> customerGroupList) {
        this.customerGroupList = customerGroupList;
    }

    public String getCust_name() {
        return cust_name;
    }

    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
    }
}
