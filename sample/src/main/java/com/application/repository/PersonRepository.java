package com.application.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.application.model.Person;


public interface PersonRepository extends CrudRepository<Person, Long> {

Page<Person> findAll(Pageable pageable);

List<Person> findByLastName(@Param("lastName") String name);

List<Person> findByFirstName(@Param("firstName") String name);

List<Person> findByCompany(@Param("company") String name);

List<Person> findByEmail(@Param("email") String name);


}