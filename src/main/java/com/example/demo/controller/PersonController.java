package com.example.demo.controller;

import com.example.demo.entity.Person;
import com.example.demo.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("person")
@AllArgsConstructor
public class PersonController {

    private final PersonService personService;

    @PostMapping(value = "", consumes =  "application/json", produces = "application/json")
    public Person create(@RequestBody Person person) {
        return personService.create(person);
    }

    @GetMapping(value = "{id}", produces = "application/json")
    public Person getOne(@PathVariable Long id) {
        Optional<Person> person = personService.getOne(id);

        if(person.isPresent()) {
            return person.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "", produces = "application/json")
    public List<Person> getAll() {
        return personService.getAll();
    }

    @PostMapping(value = "search", consumes =  "application/json", produces = "application/json")
    public List<Person> search(@RequestBody SearchQuery query) {
        return personService.search(query.lastName);
    }

    public record SearchQuery(String lastName) {
    }
}
