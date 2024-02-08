package com.example.demo.services;

import com.example.demo.dto.request.PersonRequest;
import com.example.demo.dto.response.PersonResponse;
import com.example.demo.model.Person;

import java.util.List;

public interface PersonService {
    List<PersonResponse> getAllPerson();

    List<PersonResponse> getPersonByName(String name);

    Person getPersonByID(Long id);

    PersonResponse updateName(String name, String newname);

    // PersonResponse updatePerson(String name, String userName , String email , String phone);
    PersonResponse addNewPerson(PersonRequest personRequest);

    PersonResponse updateuserName(String userName, String newusername);
}
