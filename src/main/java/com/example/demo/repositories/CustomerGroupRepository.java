package com.example.demo.repositories;

import com.example.demo.model.CustomerGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CustomerGroupRepository extends JpaRepository<CustomerGroup,Long> {
    @Query("select g from CustomerGroup g where g.group_name=?1")
    Optional<CustomerGroup> findGroupByName(String group_name);
}
