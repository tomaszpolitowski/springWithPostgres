package com.example.demo.service;

import com.example.demo.dao.PersonDao;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {
    private final PersonDao personDao;
@Autowired
    public PersonService(@Qualifier("postgres") PersonDao personDao) {
        this.personDao = personDao;
    }

    public int addPerson(Person person){return personDao.insertPerson(person);}

    public List<Person> getAllPerson(){
    return personDao.selectAllPeople();
    }
    public Optional<Person> findPerson(UUID id){
    return personDao.selectPerson(id);
    }


    public void deletePerson(UUID id){
    personDao.deletePerson(id);
    }


public void updatePerson(UUID id, Person person){
    personDao.updatePerson(id,person);
}
}

