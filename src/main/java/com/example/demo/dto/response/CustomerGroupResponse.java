package com.example.demo.dto.response;

import com.example.demo.model.Customer;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerGroupResponse {
    private Long group_id;
    private String group_name;
    private Set<Customer> customerList=new HashSet<>();
}
