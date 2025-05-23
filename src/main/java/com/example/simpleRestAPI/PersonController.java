package com.example.simpleRestAPI;

import com.example.simpleRestAPI.service.PersonService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }
    @RequestMapping("/hello/{name}")
    String hello(@PathVariable String name) {
        this.personService.add(new Person(name));
        return "Hello, " + name + "!";
    }
    @RequestMapping("/log")
    public List<Person> personRegister() {

        return  personService.list();
    }
}
