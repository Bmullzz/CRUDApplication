package io.zipcoder.crudapp.controller;

import io.zipcoder.crudapp.person.Person;
import io.zipcoder.crudapp.repo.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brianmullin on 6/15/17.
 */

@RestController
@RequestMapping("/person")
public class PersonController { //handles requests

    @Autowired
    private PersonRepository personRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Person>> getAllPeople(){
        List<Person> people = new ArrayList<>();
        personRepository.findAll().forEach(people::add);
        if(people.isEmpty()){
            return new ResponseEntity(people, HttpStatus.OK);
        }
        return new ResponseEntity<List<Person>>(people, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getPerson(@PathVariable("id") int id){
        Person person = personRepository.findOne(id);
        if(person == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Person>(person, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePerson(@PathVariable("id") int id){
        if(!personRepository.exists(id)){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }else{
            personRepository.delete(id);
        }
        return new ResponseEntity<Person>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> updatePerson(@RequestBody Person person){
        try{
            if(!personRepository.exists(person.getId())) {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        }catch(NullPointerException npe){
            npe.getMessage();
        }

        Person p = personRepository.findOne(person.getId());
        p.setName(person.getName());
        p.setAge(person.getAge());
        personRepository.save(p);

        return new ResponseEntity<Person>(person, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> placePerson(@RequestBody Person person){
        try {
            if(personRepository.exists(person.getId())) {
                return new ResponseEntity(HttpStatus.CONFLICT);
            }
        }catch(NullPointerException npe){
            npe.getMessage();
        }
        personRepository.save(person);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation();
    }





}
