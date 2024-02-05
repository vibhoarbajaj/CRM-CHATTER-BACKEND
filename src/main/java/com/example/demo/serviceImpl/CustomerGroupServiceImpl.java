package com.example.demo.serviceImpl;

import com.example.demo.dto.request.CustomerGroupRequest;
import com.example.demo.dto.response.CustomerGroupResponse;
import com.example.demo.model.Customer;
import com.example.demo.model.CustomerGroup;
import com.example.demo.repositories.CustomerGroupRepository;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.services.CustomerGroupService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomerGroupServiceImpl implements CustomerGroupService {
    @Autowired
    private CustomerGroupRepository customerGroupRepository;

    @Autowired
    private CustomerRepository customerRepository;

//    public CustomerGroupServiceImpl(CustomerGroupRepository customerGroupRepository) {
//        this.customerGroupRepository = customerGroupRepository;
//    }

    public List<CustomerGroupResponse> getAllGroups(){
        List<CustomerGroup> customerGroupList = customerGroupRepository.findAll();
//        System.out.println(customerList);
        List<CustomerGroupResponse> customerGroupResponseList = new ArrayList<>();

        for (CustomerGroup customerGroup : customerGroupList) {
            CustomerGroupResponse customerGroupResponse = new CustomerGroupResponse();
            BeanUtils.copyProperties(customerGroup, customerGroupResponse);
            customerGroupResponseList.add(customerGroupResponse);
        }

        return customerGroupResponseList;
    }

    public CustomerGroupResponse addNewGroup(CustomerGroupRequest customerGroupRequest){
      //  System.out.println(customerGroupRequest);
       // System.out.println(customerGroupRequest.getCustomer_group_name());
        Optional<CustomerGroup> groupByName = customerGroupRepository.findGroupByName(customerGroupRequest.getCustomer_group_name());
        if (groupByName.isPresent()) {
            throw new IllegalStateException("Group already exists!!");
        }
        System.out.println(customerGroupRequest);

        CustomerGroup customerGroup = new CustomerGroup();
        BeanUtils.copyProperties(customerGroupRequest, customerGroup);
       // System.out.println(customerGroup);
        CustomerGroup savedCustomerGroup = customerGroupRepository.save(customerGroup);

        CustomerGroupResponse customerGroupResponse = new CustomerGroupResponse();
        BeanUtils.copyProperties(savedCustomerGroup, customerGroupResponse);

        return customerGroupResponse;
    }

    public CustomerGroupResponse addCustomerInGroup(Long cust_id, Long group_id)
    {
        Set<Customer> customerSet=null;
        CustomerGroup customerGroup=customerGroupRepository.findById(group_id).get();
        Customer customer=customerRepository.findById(cust_id).get();
        customerSet=customerGroup.getCustomerList();
        customerSet.add(customer);
        customerGroup.setCustomerList(customerSet);
        CustomerGroup savedCustomerGroup=customerGroupRepository.save(customerGroup);

        CustomerGroupResponse customerGroupResponse = new CustomerGroupResponse();
        BeanUtils.copyProperties(savedCustomerGroup, customerGroupResponse);

        return customerGroupResponse;
    }
}
