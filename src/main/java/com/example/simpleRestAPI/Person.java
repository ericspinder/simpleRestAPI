package com.example.simpleRestAPI;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Person {

    @Id
    private String name;

    private int timesSeen = 0; // Counter for how many times this person has been seen

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Friend> friendships = new HashSet<>();

    @OneToMany(mappedBy = "friendPerson", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Friend> friendOf = new HashSet<>();

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
        return friendships.stream()
                .map(Friend::getFriendPerson)
                .collect(Collectors.toSet());
    }

    public void addFriend(Person friend) {
        Friend friendship = new Friend(this, friend);
        this.friendships.add(friendship);
        Friend reverseFriendship = new Friend(friend, this);
        friend.friendships.add(reverseFriendship);
    }

    public void addFriend(Person friend, String relationshipDescription) {
        Friend friendship = new Friend(this, friend);
        friendship.setRelationshipDescription(relationshipDescription);
        this.friendships.add(friendship);

        Friend reverseFriendship = new Friend(friend, this);
        reverseFriendship.setRelationshipDescription(relationshipDescription);
        friend.friendships.add(reverseFriendship);
    }

    public void removeFriend(Person friend) {
        this.friendships.removeIf(friendship -> friendship.getFriendPerson().equals(friend));
        friend.friendships.removeIf(friendship -> friendship.getFriendPerson().equals(this));
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

    @JsonIgnore
    public Set<Friend> getFriendships() {
        return friendships;
    }

    @JsonIgnore
    public Set<Friend> getFriendOf() {
        return friendOf;
    }
}