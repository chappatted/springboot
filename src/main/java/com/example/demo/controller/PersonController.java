package com.example.demo.controller;

import com.example.demo.entity.Address;
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

    @PutMapping(value = "{id}/address", consumes =  "application/json", produces = "application/json")
    public Address addAddress(@PathVariable Long id, @RequestBody Address address) {
        Optional<Address> newAddress = personService.addAddress(id, address);

        if(newAddress.isPresent()) {
            return newAddress.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "{id}/addresses", consumes =  "application/json", produces = "application/json")
    public List<Address> addAddresses(@PathVariable Long id, @RequestBody List<Address> addresses) {
        List<Address> newAddresses = personService.addAddresses(id, addresses);

        if(!newAddresses.isEmpty()) {
            return newAddresses;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
