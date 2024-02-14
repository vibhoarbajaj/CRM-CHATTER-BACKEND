package com.example.demo.services;

import com.example.demo.dto.request.PersonRequest;
import com.example.demo.dto.response.PersonResponse;
import com.example.demo.model.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface PersonService {
    List<PersonResponse> getAllPerson();

    ResponseEntity<?> getPersonByName(String name);

    ResponseEntity<?> getPersonByID(Long id);

   // PersonResponse updateName(String name, String newname, String userName);
    PersonResponse updatePerson(Long id , PersonRequest personRequest);
    // PersonResponse updatePerson(String name, String userName , String email , String phone);
    ResponseEntity<?> addNewPerson(PersonRequest personRequest);

    //PersonResponse updateuserName(String userName, String newusername);

    ResponseEntity<?> getPersonByuname(String userName);
}
