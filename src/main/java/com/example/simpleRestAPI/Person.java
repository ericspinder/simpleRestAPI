package com.example.simpleRestAPI;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Person {

    @Id
    private String name;

    private int timesSeen = 0; // Counter for how many times this person has been seen

    public Person() {

    }
    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getTimesSeen() {
        return timesSeen;
    }

    public void setTimesSeen(int timesSeen) {
        this.timesSeen = timesSeen;
    }

    public void incrementTimesSeen() {
        this.timesSeen++;
    }
}