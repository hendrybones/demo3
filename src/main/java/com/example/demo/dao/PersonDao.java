package com.example.demo.dao;

import com.example.demo.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {
    //all operations performed in a database
    int insertPerson(UUID id, Person person);// insert a person with a given id

    default  int insertPerson(Person person){//insert a person with a random generated id
        UUID id=UUID.randomUUID();
        return  insertPerson(id,person);
    }
    List<Person> selectAllPeople();
    Optional<Person>selectPersonById(UUID id);
    int deletePersonById(UUID id);
    int updatePersonById(UUID id, Person person);
}
