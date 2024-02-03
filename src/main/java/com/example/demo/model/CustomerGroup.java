package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table
public class CustomerGroup {
    @Id
    @Column(name="group_id",unique = true,nullable = false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "group_sequence")
    @SequenceGenerator(name = "group_sequence", sequenceName = "group_sequence",allocationSize = 1)
    private Long group_id;
    private String group_name;

    public CustomerGroup() {
    }

    public CustomerGroup(Long group_id, String group_name) {
        this.group_id = group_id;
        this.group_name = group_name;
    }

    public Long getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Long cust_group_id) {
        this.group_id = cust_group_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String cust_group_name) {
        this.group_name = cust_group_name;
    }


    @Override
    public String toString() {
        return "CustomerGroup{" +
                "group_id=" + group_id +
                ", group_name='" + group_name + '\'' +
                '}';
    }
}
