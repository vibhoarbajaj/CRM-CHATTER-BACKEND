package com.example.demo.controllers;

import com.example.demo.dto.request.PersonRequest;
import com.example.demo.dto.response.PersonResponse;
import com.example.demo.model.Person;
import com.example.demo.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person")
@CrossOrigin(origins ="*")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("/fetchAllPerson")
    public List<PersonResponse> getPerson() {
        return personService.getAllPerson();
    }

    @GetMapping("/filter/{name}")
    public ResponseEntity<?> getPersonByName(@PathVariable(value = "name", required = true) String name) {
        return personService.getPersonByName(name);
    }


    @GetMapping("/fetchPersonById/{id}")
    public ResponseEntity<?> getPersonById(@PathVariable("id") Long id) {
        return personService.getPersonByID(id);
    }
    @GetMapping("/fetchPersonByUsername/{username}")
    public ResponseEntity<?> getPersonByUserName(@PathVariable("username") String userName) {
        return personService.getPersonByuname(userName);}
    @PostMapping("/addNewPerson")
    public ResponseEntity<?> addNewPerson(@RequestBody PersonRequest personRequest) {
        return personService.addNewPerson(personRequest);
    }

    @PutMapping("/updatePerson/{id}")
    public PersonResponse updatePerson(@PathVariable("id") Long id , @RequestBody PersonRequest personRequest){
        return personService.updatePerson(id, personRequest);
    }

}
