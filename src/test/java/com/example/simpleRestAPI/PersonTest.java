package com.example.simpleRestAPI;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonTest {

    @Test
    void testPersonCreation() {
        Person person = new Person("John");
        assertEquals("John", person.getName());
        assertEquals(0, person.getTimesSeen()); // Counter should initialize to 1
    }

    @Test
    void testIncrementTimesSeen() {
        Person person = new Person("Jane");
        person.incrementTimesSeen();
        assertEquals(1, person.getTimesSeen()); // Counter should increment correctly
    }

    @Test
    void testSettersAndGetters() {
        Person person = new Person();
        person.setName("Alice");
        person.setTimesSeen(5);
        assertEquals("Alice", person.getName());
        assertEquals(5, person.getTimesSeen());
    }
}
