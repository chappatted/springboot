package com.example.demo.repository;

import com.example.demo.entity.Person;
import org.hibernate.query.ResultListTransformer;

import java.util.List;

public class PersonListTransformer implements ResultListTransformer<Object> {

    @Override
    public List<Object> transformList(List<Object> list) {
        return list.stream().map(obj -> {
            Object[] entity = (Object[]) obj;

            Long id = entity.length > 0 ? (Long) entity[0] : null;
            String firstName = entity.length > 1 ? (String) entity[1] : null;
            String lastName = entity.length > 2 ? (String) entity[2] : null;
            String email = entity.length > 3 ? (String) entity[3] : null;

            return (Object) new Person(id, firstName, lastName, email, null, null, List.of());
        }).toList();
    }
}
