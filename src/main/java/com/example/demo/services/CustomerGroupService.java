package com.example.demo.services;

import com.example.demo.dto.request.CustomerGroupRequest;
import com.example.demo.dto.response.CustomerGroupResponse;

import java.util.List;

public interface CustomerGroupService {
    List<CustomerGroupResponse> getAllGroups();

    CustomerGroupResponse addNewGroup(CustomerGroupRequest customerGroupRequest);

    CustomerGroupResponse addCustomerInGroup(Long cust_id,Long group_id);
}
