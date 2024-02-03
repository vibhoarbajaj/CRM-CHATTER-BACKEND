package com.example.demo.services;

import com.example.demo.dto.request.CustomerRequest;
import com.example.demo.dto.response.CustomerResponse;
import com.example.demo.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {

    List<CustomerResponse> getCustomers();
    CustomerResponse addNewCustomer(CustomerRequest customerRequest);
    //    ResponseEntity<?> deleteCustomer(Long id);

}
