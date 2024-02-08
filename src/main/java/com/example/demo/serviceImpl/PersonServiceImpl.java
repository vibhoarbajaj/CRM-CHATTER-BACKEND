package com.example.demo.serviceImpl;

import com.example.demo.dto.request.PersonRequest;
import com.example.demo.dto.response.PersonResponse;
import com.example.demo.model.Person;
import com.example.demo.repositories.PersonRepository;
import com.example.demo.services.PersonService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<PersonResponse> getAllPerson() {
        List<Person> allPersonList = personRepository.findAll();
        List<PersonResponse> responseList = new ArrayList<>();
        for (Person person : allPersonList) {
            PersonResponse personResponse = new PersonResponse();
            BeanUtils.copyProperties(person, personResponse);
            responseList.add(personResponse);
        }
        return responseList;
    }

    public Person getPersonByID(Long id) {

        Person getPerson = personRepository.findById(id).get();
        //  System.out.println(getPerson);
//        if(getPer){
//           throw new IllegalStateException("id doesnt exists!!");
//        }
        return getPerson;
    }

    public List<PersonResponse> getPersonByName(String name) {
        List<Person> allPersonfilter = personRepository.findFilteredName(name);
        if (allPersonfilter.isEmpty()) {
            System.out.println("No Name Found");
        }
        List<PersonResponse> responseList = new ArrayList<>();
        for (Person person : allPersonfilter) {
            PersonResponse personResponse = new PersonResponse();
            BeanUtils.copyProperties(person, personResponse);
            responseList.add(personResponse);
        }
        return responseList;
    }

    public PersonResponse addNewPerson(PersonRequest personRequest) {
        Optional<Person> personUsername = personRepository.findPersonByName(personRequest.getUserName());
        if (personUsername.isPresent()) {
           throw new IllegalStateException("error");
        }
        Person p1 = new Person();
        p1.setCreatedAt(LocalDateTime.now());
        BeanUtils.copyProperties(personRequest, p1);
        Person savedPerson = personRepository.save(p1);
        PersonResponse personResponse = new PersonResponse();
        BeanUtils.copyProperties(savedPerson, personResponse);
        return personResponse;
    }

    public PersonResponse updateName(String name, String newname,String userName) {
        Person newP = personRepository.findByuserName(userName);
        //Person newP = personRepository.findBYid(id);
        //Person newPerson = personRepository.findByName(name);
        String fetchName = newP.getName();
    if(newP==null){
    throw new IllegalStateException("No person with this id/name exists");
    }

        newP.setName(newname);
        personRepository.save(newP);
        PersonResponse personResponse = new PersonResponse();

        BeanUtils.copyProperties(newP, personResponse);

        return personResponse;
    }

    public PersonResponse updateuserName(String userName, String newusername) {

        Person newPerson = personRepository.findByuserName(userName);
       // Person prevPerson = personRepository.findByuserName(newusername);
        Optional<Person> personUsername = personRepository.findPersonByName(newusername);
        if (personUsername.isPresent()) {
            throw new IllegalStateException("This username already exists please try again");
        }
        newPerson.setUserName(newusername);
        personRepository.save(newPerson);
        PersonResponse personResponse = new PersonResponse();

        BeanUtils.copyProperties(newPerson, personResponse);

        return personResponse;
    }
}