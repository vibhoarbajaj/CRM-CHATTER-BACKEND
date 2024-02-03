package com.example.demo.controllers;

import com.example.demo.dto.request.CustomerGroupRequest;
import com.example.demo.dto.response.CustomerGroupResponse;
import com.example.demo.services.CustomerGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/group")
@CrossOrigin("*")
public class CustomerGroupController {
    @Autowired
    private final CustomerGroupService customerGroupService;


    public CustomerGroupController(CustomerGroupService customerGroupService) {
        this.customerGroupService = customerGroupService;
    }

    @GetMapping("/fetchAllGroups")
    public List<CustomerGroupResponse> getCustomerGroups() {
        return customerGroupService.getAllGroups();
    }
    @PostMapping("/addNewGroup")
    public CustomerGroupResponse registerNewCustomerGroups(@RequestBody CustomerGroupRequest customerGroupRequest){
        return customerGroupService.addNewGroup(customerGroupRequest);
    }
}
