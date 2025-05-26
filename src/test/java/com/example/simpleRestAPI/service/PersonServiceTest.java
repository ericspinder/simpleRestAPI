package com.example.simpleRestAPI.service;

import com.example.simpleRestAPI.Friend;
import com.example.simpleRestAPI.Interest;
import com.example.simpleRestAPI.Person;
import com.example.simpleRestAPI.repositories.FriendRepository;
import com.example.simpleRestAPI.repositories.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private FriendRepository friendRepository;

    @Mock
    private InterestService interestService;

    @InjectMocks
    private PersonService personService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test for new method: addFriend (new friend creation)
    @Test
    void addFriend_NewFriend_CreatesAndAdds() {
        Person person = new Person("Alice");
        when(personRepository.findById("Alice")).thenReturn(Optional.of(person));
        when(personRepository.findById("Bob")).thenReturn(Optional.empty());
        Person newFriend = new Person("Bob");
        newFriend.setTimesSeen(0);
        when(personRepository.save(any(Person.class))).thenAnswer(invocation -> invocation.getArgument(0));

        personService.addFriend("Alice", "Bob");

        assertTrue(person.getFriends().stream().anyMatch(f -> f.getName().equals("Bob")));
        verify(personRepository, times(3)).save(any(Person.class)); // Saves new friend and updated person
    }

    // Test for new method: removeFriend
    @Test
    void removeFriend_ExistingFriend_Removes() {
        Person person = new Person("Alice");
        Person friend = new Person("Bob");
        person.addFriend(friend);
        when(personRepository.findById("Alice")).thenReturn(Optional.of(person));
        when(personRepository.findById("Bob")).thenReturn(Optional.of(friend));
        when(personRepository.save(any(Person.class))).thenReturn(null);

        personService.removeFriend("Alice", "Bob");

        assertFalse(person.getFriends().contains(friend));
        verify(personRepository, times(2)).save(any(Person.class));
    }

    // Test for new method: addFriend with relationship description
    @Test
    void addFriend_WithRelationshipDescription_CreatesAndAdds() {
        Person person = new Person("Alice");
        Person friend = new Person("Bob");
        when(personRepository.findById("Alice")).thenReturn(Optional.of(person));
        when(personRepository.findById("Bob")).thenReturn(Optional.of(friend));
        when(personRepository.save(any(Person.class))).thenAnswer(invocation -> invocation.getArgument(0));

        personService.addFriend("Alice", "Bob", "College friends");

        assertTrue(person.getFriends().stream().anyMatch(f -> f.getName().equals("Bob")));
        assertEquals("College friends", person.getFriendships().iterator().next().getRelationshipDescription());
        verify(personRepository, times(2)).save(any(Person.class));
    }

    // Test for new method: getFriendsWithSharedInterests
    @Test
    void getFriendsWithSharedInterests_ReturnsMatchingFriends() {
        Person person = new Person("Alice");
        Person friend1 = new Person("Bob"); // Shares interest
        Person friend2 = new Person("Charlie"); // No shared
        person.addFriend(friend1);
        person.addFriend(friend2);
        when(personRepository.findById("Alice")).thenReturn(Optional.of(person));
        when(interestService.listInterests("Alice")).thenReturn(List.of(new Interest(person, "Reading")));
        when(interestService.listInterests("Bob")).thenReturn(List.of(new Interest(friend1, "Reading")));
        when(interestService.listInterests("Charlie")).thenReturn(List.of(new Interest(friend2, "Sports")));

        List<String> result = personService.getFriendsWithSharedInterests("Alice");

        assertEquals(1, result.size());
        assertTrue(result.contains("Bob"));
    }

    // Edge case for new method: addFriend with non-existent person
    @Test
    void addFriend_PersonNotFound_ThrowsException() {
        when(personRepository.findById("Unknown")).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> personService.addFriend("Unknown", "Bob"));
    }

    // Test for existing method: add (from original PersonService)
    @Test
    void add_ExistingPerson_IncrementsTimesSeen() {
        Person existing = new Person("Alice");
        existing.setTimesSeen(1);
        when(personRepository.findById("Alice")).thenReturn(Optional.of(existing));
        when(personRepository.save(any(Person.class))).thenReturn(existing);

        Person result = personService.add("Alice");

        assertEquals(2, result.getTimesSeen());
        verify(personRepository).save(existing);
    }
}