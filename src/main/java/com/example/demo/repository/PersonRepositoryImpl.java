package com.example.demo.repository;

import com.example.demo.entity.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<Person> search(String lastName) {
        return entityManager.createNativeQuery(" SELECT id, first_name, last_name, email" +
                                                " FROM person" +
                                                " WHERE last_name = '" + lastName + "' AND show_in_search = TRUE", Object[].class)
                .unwrap(org.hibernate.query.NativeQuery.class)
                .setResultListTransformer(new PersonListTransformer())
                .getResultList();
    }
}
