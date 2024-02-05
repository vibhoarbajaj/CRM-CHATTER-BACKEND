package com.example.demo.dto.request;

import com.example.demo.model.Customer;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class CustomerGroupRequest {
    private String group_name;
    private Set<Customer> customerList=new HashSet<>();
    public CustomerGroupRequest() {
    }

    public CustomerGroupRequest(String group_name, Set<Customer> customerList) {
        this.group_name = group_name;
        this.customerList = customerList;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }


    public Set<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(Set<Customer> customerList) {
        this.customerList = customerList;
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
