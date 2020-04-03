package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("api/v1/person")
@RestController
public class PersonController {
    private final PersonService personService;
@Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }
@PostMapping
    public void addPerson(@RequestBody @Valid @NotNull Person person){
        personService.addPerson(person);
    }
    @GetMapping
    public List<Person> getAllPerson(){
    return personService.getAllPerson();
    }
@GetMapping(path = "{id}")
    public Person findPerson(@PathVariable("id") UUID id){
    return personService.findPerson(id)
            .orElse(null);
    }
    @DeleteMapping(path="{id}")
    public void deletePerson(@PathVariable("id") UUID id){
    personService.deletePerson(id);
    }

   @PutMapping(path = "{id}")
    public void updatePerson(@PathVariable("id") UUID id, @Valid @NotNull @RequestBody Person person){
    personService.updatePerson(id,person);
    }
}
