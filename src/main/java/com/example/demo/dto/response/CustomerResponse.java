package com.example.demo.dto.response;

import com.example.demo.model.CustomerGroup;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerResponse {
    private Long cust_id;
    private String cust_name;

    private Set<CustomerGroup> customerGroupList=new HashSet<>();
}
