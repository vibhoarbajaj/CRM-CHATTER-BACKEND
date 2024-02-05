package com.example.demo.repositories;

import com.example.demo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

//    @Query(value = "SELECT c FROM Customer c WHERE c.cust_name = ?1",nativeQuery = true);  // can write like this too
    @Query("SELECT c FROM Customer c WHERE c.cust_name = ?1") // this is jpql query
    Optional<Customer> findCustomerByName(String name);

}
