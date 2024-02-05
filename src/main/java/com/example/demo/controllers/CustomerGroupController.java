package com.example.demo.controllers;

import com.example.demo.dto.request.CustomerGroupRequest;
import com.example.demo.dto.response.CustomerGroupResponse;
import com.example.demo.services.CustomerGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/group")
@CrossOrigin(origins = "*")
public class CustomerGroupController {
    @Autowired
    private CustomerGroupService customerGroupService;

//    public CustomerGroupController(CustomerGroupService customerGroupService) {
//        this.customerGroupService = customerGroupService;
//    }

    @GetMapping("/fetchAllGroups")
    public List<CustomerGroupResponse> getCustomerGroups() {
        return customerGroupService.getAllGroups();
    }
    @PostMapping("/addNewGroup")
    public CustomerGroupResponse registerNewCustomerGroups(@RequestBody CustomerGroupRequest customerGroupRequest){
        return customerGroupService.addNewGroup(customerGroupRequest);
    }
    @PutMapping("{cust_id}/group/{group_id}")
    public CustomerGroupResponse addCustomerInGroup(@PathVariable("cust_id") Long cust_id,
                                                @PathVariable("group_id") Long group_id)
    {
        return customerGroupService.addCustomerInGroup(cust_id,group_id);
    }
}
