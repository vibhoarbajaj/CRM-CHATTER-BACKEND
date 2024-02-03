package com.example.demo.serviceImpl;

import com.example.demo.dto.request.CustomerRequest;
import com.example.demo.dto.response.CustomerResponse;
import com.example.demo.model.Customer;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.services.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerResponse> getCustomers(){
        List<Customer> customerList= customerRepository.findAll();
        System.out.println(customerList);
        List<CustomerResponse> customerListResponse = new ArrayList<>();

        for (Customer customer : customerList) {
            CustomerResponse customerResponse = new CustomerResponse();
            BeanUtils.copyProperties(customer, customerResponse);
            customerListResponse.add(customerResponse);
        }

        return customerListResponse;
    }
    public CustomerResponse addNewCustomer(CustomerRequest customerRequest) {
        //System.out.println(customerRequest);
        //System.out.println(customerRequest.getCust_name());
        Optional<Customer> customerByName = customerRepository.findCustomerByName(customerRequest.getCust_name());
        //System.out.println(customerByName);
        if (customerByName.isPresent()) {
            throw new IllegalStateException("Name already exists!!");
        }

        Customer customer = new Customer();
        BeanUtils.copyProperties(customerRequest, customer);

        Customer savedCustomer = customerRepository.save(customer);

        CustomerResponse customerResponse = new CustomerResponse();
        BeanUtils.copyProperties(savedCustomer, customerResponse);

        return customerResponse;
    }
}


