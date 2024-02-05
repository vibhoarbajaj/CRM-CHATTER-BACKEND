package com.example.demo.controllers;

import com.example.demo.dto.request.CustomerRequest;
import com.example.demo.dto.response.CustomerResponse;
import com.example.demo.model.Customer;
import com.example.demo.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/customer")
@CrossOrigin(origins = "*")
public class CustomerController {
    @Autowired
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/fetchAllCustomer")
    public List<CustomerResponse> getCustomers() {
        return customerService.getCustomers();
    }
    @PostMapping("/addNewCustomer")
    public void registerNewCustomer(@RequestBody CustomerRequest customerRequest){
        customerService.addNewCustomer(customerRequest);
    }
//    @DeleteMapping(path ="{id}")
//    public void deleteCustomer(@PathVariable("id") Long id){
//        customerService.deleteCustomer(id);
//    }

//    @PutMapping(path ="{id}")
//    public void updateCustomer( @PathVariable("id") Long id ,
//                               @RequestParam(required = false) String name){
//        customerService.updateCustomer(id , name);
//
//    }
}
