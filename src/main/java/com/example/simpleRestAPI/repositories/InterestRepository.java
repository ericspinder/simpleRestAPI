package com.example.simpleRestAPI.repositories;

import com.example.simpleRestAPI.Interest;
import com.example.simpleRestAPI.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InterestRepository extends JpaRepository<Interest, Long> {
    List<Interest> findByPerson(Person person);
    Interest findByPersonAndInterest(Person person, String interest);
}