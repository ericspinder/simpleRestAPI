package com.example.simpleRestAPI;

import com.example.simpleRestAPI.service.InterestService;
import com.example.simpleRestAPI.service.PersonService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class SimpleRestAPIController {

    private final PersonService personService;
    private final InterestService interestService;

    public SimpleRestAPIController(PersonService personService, InterestService interestService) {
        this.personService = personService;
        this.interestService = interestService;
    }
    @RequestMapping("/hello/{name}")
    String hello(@PathVariable String name) {
        Person person = this.personService.add(name);
        return "Hello, " + name + "! You have been seen " + person.getTimesSeen() + " times.";
    }
    @RequestMapping("/log")
    public List<Person> personRegister() {

        return  personService.list();
    }
    @RequestMapping("/person/{name}/count")
    public String getPersonCount(@PathVariable String name) {
        Person person = personService.getPersonRepository().findById(name).orElse(null);
        if (person != null) {
            return name + " has been seen " + person.getTimesSeen() + " times.";
        } else {
            return "Person " + name + " not found.";
        }
    }
    @RequestMapping("/interest/{person}/{interest}")
    public String manageInterest(@PathVariable String person, @PathVariable String interest, @RequestParam(required = false, defaultValue = "add") String action) {
        if ("remove".equalsIgnoreCase(action)) {
            interestService.removeInterest(person, interest);
            return "Removed interest '" + interest + "' for " + person;
        } else {
            interestService.addInterest(person, interest);
            return "Added interest '" + interest + "' for " + person;
        }
    }

    @RequestMapping("/interest/{person}/list")
    public List<String> listInterests(@PathVariable String person) {
        return interestService.listInterests(person).stream()
                .map(Interest::getInterest)
                .toList();
    }
}
