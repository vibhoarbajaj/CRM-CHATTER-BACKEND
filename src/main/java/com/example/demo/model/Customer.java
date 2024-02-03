package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table
public class Customer {
    @Id
    @Column(name="cust_id",unique = true,nullable = false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "customer_sequence")
    @SequenceGenerator(name = "customer_sequence", sequenceName = "customer_sequence",allocationSize = 1)
    private Long cust_id;
    private String cust_name;

    public Customer(Long cust_id, String cust_name) {
        this.cust_id = cust_id;
        this.cust_name = cust_name;
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
