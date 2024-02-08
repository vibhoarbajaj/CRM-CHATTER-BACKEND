package com.example.demo.repositories;

import com.example.demo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query("SELECT p FROM Person p WHERE p.userName = ?1")
        // this is jpql query
    Optional<Person> findPersonByName(String username);
//@Query("select p from Person where p.id=?1")
//Person findBYid(Long id);
    @Query("select p from Person p where p.name like ?1%")
    List<Person> findFilteredName(String name);

    @Query("select p from Person p where p.name= ?1")
    Person findByName(String name);

    @Query("select p from Person p where p.userName= ?1")
    Person findByuserName(String name);
}
