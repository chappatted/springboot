package com.example.demo.service;

import com.example.demo.entity.Address;
import com.example.demo.entity.Person;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.PersonRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final AddressRepository addressRepository;

    public Person create(Person person) {
        return personRepository.save(person);
    }

    public Optional<Person> getOne(Long Id) {
        return personRepository.findById(Id);
    }

    public Optional<Address> addAddress(Long personId, Address address) {
        Optional<Person> person = personRepository.findById(personId);

        if(person.isEmpty()) {
            return Optional.empty();
        }

        address.setPerson(person.get());
        addressRepository.save(address);

        return Optional.of(address);
    }

    public List<Address> addAddresses(Long personId, List<Address> addresses) {
        Optional<Person> person = personRepository.findById(personId);

        if(person.isEmpty()) {
            return List.of();
        }

        for(Address address : addresses) {
            address.setPerson(person.get());
            addressRepository.save(address);
        }

        return addresses;
    }
}
