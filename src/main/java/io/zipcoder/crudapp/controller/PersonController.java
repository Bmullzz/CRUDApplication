package io.zipcoder.crudapp.controller;

import io.zipcoder.crudapp.person.Person;
import io.zipcoder.crudapp.repo.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by brianmullin on 6/15/17.
 */

@RestController
@RequestMapping("/person")
public class PersonController { //handles requests

    @Autowired
    private PersonRepository personRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Person> getAllPeople(){
        return personRepository.findAll();
    }



}
