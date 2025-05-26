package com.example.simpleRestAPI;

import jakarta.persistence.*;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person_name")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "friend_name")
    private Person friendPerson;

    private Instant createdDate;

    private String relationshipDescription;

    public Friend() {
        this.createdDate = Instant.now();
    }

    public Friend(Person person, Person friendPerson) {
        this.person = person;
        this.friendPerson = friendPerson;
        this.createdDate = Instant.now();
    }

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

    public Person getFriendPerson() {
        return friendPerson;
    }

    public void setFriendPerson(Person friendPerson) {
        this.friendPerson = friendPerson;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getRelationshipDescription() {
        return relationshipDescription;
    }

    public void setRelationshipDescription(String relationshipDescription) {
        this.relationshipDescription = relationshipDescription;
    }
}
