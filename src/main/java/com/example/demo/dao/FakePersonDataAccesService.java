package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository("fakeDao")
public class FakePersonDataAccesService implements PersonDao{
    private static List<Person> DB = new ArrayList<>();
    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id,person.getName()));
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        return DB;
    }

    @Override
    public Optional<Person> selectPerson(UUID id) {
        return DB.stream()
                .filter(person->person.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deletePerson(UUID id) {
        Optional<Person> personMaybe = selectPerson(id);
        if (!personMaybe.isPresent()) return 0;
        DB.remove(personMaybe.get());
        return 1;
    }

    @Override
    public int updatePerson(UUID id, Person person) {
        return selectPerson(id).map(p->{
            int indexOfPersonToUpdate=DB.indexOf(p);
            if (indexOfPersonToUpdate>= 0) {DB.set(indexOfPersonToUpdate,new Person(id,person.getName())); return 1;}
return 0;
        }).orElse(0);
    }
}
