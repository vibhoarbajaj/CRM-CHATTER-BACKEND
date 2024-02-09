package com.example.demo.controllers;

import com.example.demo.dto.request.PersonRequest;
import com.example.demo.dto.response.PersonResponse;
import com.example.demo.model.Person;
import com.example.demo.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person")
@CrossOrigin("*")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("/fetchAllPerson")
    public List<PersonResponse> getPerson() {
        return personService.getAllPerson();
    }

    @GetMapping("/filter/{name}")
    public List<PersonResponse> getPersonByName(@PathVariable(value = "name", required = false) String name) {
        return personService.getPersonByName(name);

    }

    @GetMapping("/fetchPersonById/{id}")
    public Person getPersonById(@PathVariable("id") Long id) {
        return personService.getPersonByID(id);
    }
    @GetMapping("/fetchPersonByUsername/{username}")
    public Person getPersonById(@PathVariable("username") String userName) {
        return personService.getPersonByuname(userName);}
    @PostMapping("/addNewPerson")
    public PersonResponse addNewPerson(@RequestBody PersonRequest personRequest) {
        return personService.addNewPerson(personRequest);
    }

    //    @PutMapping("/{name}/{userName}/{email}/{phone}")
//    public PersonResponse updatePerson(@PathVariable("name") String name ,
//                                       @PathVariable("userName") String userName ,
//                                       @PathVariable("email") String email,
//                                       @PathVariable("phone") String phone)
//    {
//        return personService.updatePerson(name , userName,  email , phone);
//    }
    @PutMapping("/{name}/updateName/{newname}/{username}")
    public PersonResponse updateName(@PathVariable("name") String name, @PathVariable("newname") String newname,@PathVariable("username") String userName) {
        return personService.updateName(name, newname , userName);
    }

    @PutMapping("/{userName}/updateUserName/{newusername}")
    public PersonResponse updateuserName(@PathVariable("userName") String userName, @PathVariable("newusername") String newusername) {
        return personService.updateuserName(userName, newusername);
    }


}
