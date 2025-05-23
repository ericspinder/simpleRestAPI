package com.example.simpleRestAPI.service;

import com.example.simpleRestAPI.Person;
import com.example.simpleRestAPI.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    public List<Person> list() {
        return personRepository.findAll();
    }

    public PersonRepository getBookRepository() {
        return personRepository;
    }
    public void add(Person person) {
        this.personRepository.save(person);
    }
}
