package com.example.simpleRestAPI.service;

import com.example.simpleRestAPI.Interest;
import com.example.simpleRestAPI.Person;
import com.example.simpleRestAPI.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    private final InterestService interestService; // New dependency for interest checks

    @Autowired
    public PersonService(PersonRepository personRepository, InterestService interestService) {
        this.personRepository = personRepository;
        this.interestService = interestService;
    }
    public List<Person> list() {
        return personRepository.findAll();
    }

    public PersonRepository getPersonRepository() {
        return personRepository;
    }
    public Person add(String name) {
        Person person = personRepository.findById(name).orElse(new Person(name));
        person.incrementTimesSeen();
        this.personRepository.save(person);
        return person;
    }
    public void addFriend(String personName, String friendName) {
        Person person = personRepository.findById(personName)
                .orElseThrow(() -> new RuntimeException("Person not found"));
        Person friend = personRepository.findById(friendName)
                .orElseGet(() -> {
                    Person newFriend = new Person(friendName);
                    newFriend.setTimesSeen(0); // New person hasn't been seen
                    return personRepository.save(newFriend);
                });
        if (!person.getFriends().contains(friend)) {
            person.addFriend(friend);
            personRepository.save(person);
            personRepository.save(friend); // Save both for relationship
        }
    }

    public void removeFriend(String personName, String friendName) {
        Person person = personRepository.findById(personName)
                .orElseThrow(() -> new RuntimeException("Person not found"));
        Person friend = personRepository.findById(friendName)
                .orElseThrow(() -> new RuntimeException("Friend not found"));
        if (person.getFriends().contains(friend)) {
            person.removeFriend(friend);
            personRepository.save(person);
            personRepository.save(friend); // Save both for relationship
        }
    }

    public List<String> getFriendsWithSharedInterests(String personName) {
        Person person = personRepository.findById(personName)
                .orElseThrow(() -> new RuntimeException("Person not found"));
        List<Interest> personInterests = interestService.listInterests(personName);
        Set<String> personInterestSet = personInterests.stream()
                .map(Interest::getInterest)
                .collect(Collectors.toSet());

        List<String> sharedFriends = new ArrayList<>();
        for (Person friend : person.getFriends()) {
            List<Interest> friendInterests = interestService.listInterests(friend.getName());
            boolean hasShared = friendInterests.stream()
                    .anyMatch(i -> personInterestSet.contains(i.getInterest()));
            if (hasShared) {
                sharedFriends.add(friend.getName());
            }
        }
        return sharedFriends;
    }
}
