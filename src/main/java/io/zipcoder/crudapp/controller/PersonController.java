package io.zipcoder.crudapp.controller;

import io.zipcoder.crudapp.person.Person;
import io.zipcoder.crudapp.repo.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Person getPerson(@PathVariable("id") int id){
        return personRepository.findOne(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deletePerson(@PathVariable("id") int id){
        personRepository.delete(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updatePerson(@RequestBody Person person){
        Person p = personRepository.findOne(person.getId());
        p.setName(person.getName());
        p.setAge(person.getAge());
        personRepository.save(p);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void placePerson(@RequestBody Person person){
        personRepository.save(person);
    }





}
