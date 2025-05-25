package com.example.simpleRestAPI;

import com.example.simpleRestAPI.service.InterestService;
import com.example.simpleRestAPI.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SimpleRestAPIController.class)
class SimpleRestAPIControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PersonService personService;

    @MockitoBean
    private InterestService interestService;

    // Test for new endpoint: manageFriend (add)
    @Test
    void manageFriend_Add_Success() throws Exception {
        doNothing().when(personService).addFriend("Alice", "Bob");

        mockMvc.perform(get("/person/Alice/friend/Bob").param("action", "add"))
                .andExpect(status().isOk())
                .andExpect(content().string("Added friend 'Bob' for Alice"));
    }

    // Test for new endpoint: manageFriend (remove, with error)
    @Test
    void manageFriend_Remove_FriendNotFound() throws Exception {
        doThrow(new RuntimeException("Friend not found")).when(personService).removeFriend("Alice", "Bob");

        mockMvc.perform(get("/person/Alice/friend/Bob").param("action", "remove"))
                .andExpect(status().is5xxServerError()); // Assumes exception handler; adjust if custom
    }

    // Test for new endpoint: getSharedInterestFriends
    @Test
    void getSharedInterestFriends_Success() throws Exception {
        when(personService.getFriendsWithSharedInterests("Alice")).thenReturn(List.of("Bob"));

        mockMvc.perform(get("/person/Alice/friends/shared-interests"))
                .andExpect(status().isOk())
                .andExpect(content().json("[\"Bob\"]"));
    }

    // Test for existing endpoint: hello
    @Test
    void hello_Success() throws Exception {
        Person person = new Person("Alice");
        person.setTimesSeen(1);
        when(personService.add("Alice")).thenReturn(person);

        mockMvc.perform(get("/hello/Alice"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, Alice! You have been seen 1 times."));
    }

    // Test for existing endpoint: manageInterest (add)
    @Test
    void manageInterest_Add_Success() throws Exception {
        doNothing().when(interestService).addInterest("Alice", "Reading");

        mockMvc.perform(get("/interest/Alice/Reading").param("action", "add"))
                .andExpect(status().isOk())
                .andExpect(content().string("Added interest 'Reading' for Alice"));
    }

    // Edge case for existing endpoint: manageInterest with invalid action
    @Test
    void manageInterest_InvalidAction_DefaultsToAdd() throws Exception {
        doNothing().when(interestService).addInterest("Alice", "Reading");

        mockMvc.perform(get("/interest/Alice/Reading").param("action", "invalid"))
                .andExpect(status().isOk())
                .andExpect(content().string("Added interest 'Reading' for Alice"));
    }
}