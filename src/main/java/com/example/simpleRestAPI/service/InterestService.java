package com.example.simpleRestAPI.service;

import com.example.simpleRestAPI.Interest;
import com.example.simpleRestAPI.Person;
import com.example.simpleRestAPI.repositories.InterestRepository;
import com.example.simpleRestAPI.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterestService {

    private final InterestRepository interestRepository;
    private final PersonRepository personRepository;

    @Autowired
    public InterestService(InterestRepository interestRepository, PersonRepository personRepository) {
        this.interestRepository = interestRepository;
        this.personRepository = personRepository;
    }

    public void addInterest(String personName, String interest) {
        Person person = personRepository.findById(personName).orElseThrow(() -> new RuntimeException("Person not found"));
        if (interestRepository.findByPersonAndInterest(person, interest) == null) {
            interestRepository.save(new Interest(person, interest));
        }
    }

    public void removeInterest(String personName, String interest) {
        Person person = personRepository.findById(personName).orElseThrow(() -> new RuntimeException("Person not found"));
        Interest existingInterest = interestRepository.findByPersonAndInterest(person, interest);
        if (existingInterest != null) {
            interestRepository.delete(existingInterest);
        }
    }

    public List<Interest> listInterests(String personName) {
        Person person = personRepository.findById(personName).orElseThrow(() -> new RuntimeException("Person not found"));
        return interestRepository.findByPerson(person);
    }
}