package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class Customer {
    @Id
    @Column(name="cust_id",unique = true,nullable = false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "customer_sequence")
    @SequenceGenerator(name = "customer_sequence", sequenceName = "customer_sequence",allocationSize = 1)
    private Long cust_id;
    private String cust_name;

    @JsonIgnore // so that customer table is ignoring customerGroupList from Customer model
    @ManyToMany(mappedBy = "customerList")
    private Set<CustomerGroup> customerGroupList=new HashSet<>();

    public Set<CustomerGroup> getCustomerGroupList() {
        return customerGroupList;
    }

    public void setCustomerGroupList(Set<CustomerGroup> customerGroupList) {
        this.customerGroupList = customerGroupList;
    }

    public Customer(Long cust_id, String cust_name, Set<CustomerGroup> customerGroupList) {
        this.cust_id = cust_id;
        this.cust_name = cust_name;
        this.customerGroupList = customerGroupList;
    }

    public Customer() {
    }

    public Long getCust_id() {
        return cust_id;
    }

    public void setCust_id(Long cust_id) {
        this.cust_id = cust_id;
    }

    public String getCust_name() {
        return cust_name;
    }

    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cust_id=" + cust_id +
                ", cust_name='" + cust_name + '\'' +
                '}';
    }
}
