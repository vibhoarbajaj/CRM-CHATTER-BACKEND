package com.example.demo.serviceImpl;

import com.example.demo.dto.request.PersonRequest;
import com.example.demo.dto.response.PersonResponse;
import com.example.demo.model.Person;
import com.example.demo.repositories.PersonRepository;
import com.example.demo.services.PersonService;
//import exception.person.GlobalException;
//import exception.person.IdException;
//mport exception.person.NewGlobalException;
import exception.person.GlobalExceptionHandler;
import exception.person.ResourceNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.IllegalFormatConversionException;
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

    public ResponseEntity<?> getPersonByID(Long id) {

        Person getPerson = personRepository.findBYid(id);
        if(getPerson == null){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No user with " + id+ " id exists");
      // throw new NewGlobalException.ResourceNotFoundException("Nothing");
        }
        return ResponseEntity.status(HttpStatus.OK).body(getPerson);
    }

    public ResponseEntity<?> getPersonByName(String name) {
        // System.out.println("name"+name);

        if (name.trim().isEmpty() || name.isEmpty()) {
            System.out.println("hah");
       //  throw new ResourceNotFoundException("piyush");
       return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Name is  not acceptable");
        }
        List<Person> allPersonfilter = personRepository.findFilteredName(name);

        List<PersonResponse> responseList = new ArrayList<>();
        for (Person person : allPersonfilter) {
            PersonResponse personResponse = new PersonResponse();
            BeanUtils.copyProperties(person, personResponse);
            responseList.add(personResponse);
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }

    public ResponseEntity<?> getPersonByuname(String userName) {
        Person fetchUsername = personRepository.findByuserName(userName);
        if (fetchUsername == null) {
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Username Exists");
        }
        return ResponseEntity.status(HttpStatus.OK).body(fetchUsername);
    }

    public boolean validatePhone(Person p1) {
        boolean tmpBool = true;
        if (p1.getPhone().length() != 10) {
            return false;
        }
        String tmp = p1.getPhone();

        for (int i = 0; i < tmp.length(); i++) {
            char currentChar = tmp.charAt(i);
            if ((currentChar >= 'a' && currentChar <= 'z') || (currentChar >= 'A' && currentChar <= 'Z')) {
                tmpBool = false;
                break;
            }
        }
        return tmpBool;
    }

    public ResponseEntity<?> addNewPerson(PersonRequest personRequest) {
        Optional<Person> personUsername = personRepository.findPersonByName(personRequest.getUserName());
        if (personUsername.isPresent()) {
            //throw new IllegalStateException("Username already exists");
          return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("Username Already exists");
//            throw new IllegalStateException("error");
        }
        Person p1 = new Person();
        p1.setCreatedAt(LocalDateTime.now());
        BeanUtils.copyProperties(personRequest, p1);

        boolean b1 = validatePhone(p1);
        if (b1) {
            Person savedPerson = personRepository.save(p1);
            PersonResponse personResponse = new PersonResponse();
            BeanUtils.copyProperties(savedPerson, personResponse);
            return ResponseEntity.status(HttpStatus.OK).body(personResponse);
        } else {
            //throw new IllegalStateException("Phone number not valid");
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("This Phone number is not acceptable");
        }
    }

    public PersonResponse updatePerson(Long id, PersonRequest personRequest) {
        Person existingPerson = personRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Person with id " + id + " not found"));

        existingPerson.setEmail(personRequest.getEmail());
        existingPerson.setPhone(personRequest.getPhone());
        existingPerson.setName(personRequest.getName());
        existingPerson.setUserName(personRequest.getUserName());
        Person savePerson = personRepository.save(existingPerson);

        PersonResponse personResponse = new PersonResponse();
        BeanUtils.copyProperties(savePerson, personResponse);
        return personResponse;
    }
}