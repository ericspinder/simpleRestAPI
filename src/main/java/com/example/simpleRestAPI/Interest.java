package com.example.simpleRestAPI;

import jakarta.persistence.*;

@Entity
public class Interest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person_name", referencedColumnName = "name")
    private Person person;

    private String interest;

    public Interest() {
    }

    public Interest(Person person, String interest) {
        this.person = person;
        this.interest = interest;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }
}