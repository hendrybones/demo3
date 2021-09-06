package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository("personData")
public class PersonDataAccessService  implements PersonDao {
    private static List<Person> Db=new ArrayList<>();//help in storing person in db

    @Override
    public int insertPerson(UUID id, Person person) {
        Db.add(new Person(id,person.getName()));
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        return Db;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return Db.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> personId=selectPersonById(id);
        if (personId.isEmpty()){
            return 0;
        }
        Db.remove(personId.get());
        return 1;
    }

    @Override
    public int updatePersonById(UUID id, Person update) {
        return selectPersonById(id)
                .map(person -> {
                    int indexOfPersonToUpdate=Db.indexOf(person);
                    if (indexOfPersonToUpdate >= 0) {
                        Db.set(indexOfPersonToUpdate ,new Person(id,update.getName()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }
}
