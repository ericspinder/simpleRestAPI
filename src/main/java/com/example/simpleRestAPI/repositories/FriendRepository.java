package com.example.simpleRestAPI.repositories;

import com.example.simpleRestAPI.Friend;
import com.example.simpleRestAPI.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {
    List<Friend> findByPerson(Person person);
    List<Friend> findByFriendPerson(Person friendPerson);
}