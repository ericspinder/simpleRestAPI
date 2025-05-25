package com.example.simpleRestAPI;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import org.apache.catalina.User;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Person {

    @Id
    private String name;

    private int timesSeen = 0; // Counter for how many times this person has been seen

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "person_friends",
            joinColumns = @JoinColumn(name = "person_name"),
            inverseJoinColumns = @JoinColumn(name = "friend_name")
    )
    private Set<Person> friends = new HashSet<>();
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

    @JsonIgnore
    public Set<Person> getFriends() {
        return friends;
    }

    public void addFriend(Person friend) {
        this.friends.add(friend);
        friend.getFriends().add(this); // Ensure bidirectional relationship
    }

    public void removeFriend(Person friend) {
        this.friends.remove(friend);
        friend.getFriends().remove(this); // Ensure bidirectional relationship
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