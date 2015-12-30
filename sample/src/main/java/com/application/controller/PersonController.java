package com.application.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.application.exception.PersonNotFound;
import com.application.model.Person;
import com.application.repository.PersonRepository;

@RestController
@RequestMapping(value = "/person")
public class PersonController {

@Autowired
private PersonRepository personRepository;

	@RequestMapping(value = "/", method=RequestMethod.GET)
	@ResponseBody
	public List<Person> getAll() {
		
		List<Person> response = new ArrayList<Person>();
		Iterable<Person>results = personRepository.findAll();
		if (results != null)
		{
			for (Person p : results)
				response.add(p);
		}
		
		return response;
	}

	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	@ResponseBody
	public Person findOne(@PathVariable("id") Long id) throws PersonNotFound
	{
		Person p = personRepository.findOne(id);
		
		if (p == null)
			throw new PersonNotFound();
		
		return p; 
	}
	
	@RequestMapping(value = "/{id}", method=RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable("id") Long id) throws PersonNotFound
	{
		Person p = personRepository.findOne(id);
		
		if (p == null)
			throw new PersonNotFound();
		
		personRepository.delete(id);
	}
	
	@RequestMapping(value = "/" , method = {RequestMethod.POST, RequestMethod.PUT})
	@ResponseBody
	public Person save(@RequestBody Person person) throws PersonNotFound
	{
		if (person.getId() != null)
		{
			Person p = personRepository.findOne(person.getId());
			if (p == null)
			{
				throw new PersonNotFound();
			}			
			else
			{
				personRepository.save(person);
			}
		}
		else
		{
			personRepository.save(person);
		}
		
		return person;
	}
	
	@RequestMapping(value = "/lastname/{name}", method=RequestMethod.GET)
	@ResponseBody
	public List<Person> findByLastName(@PathVariable("name") String name)
	{
		return personRepository.findByLastName(name);
	}
	
	@RequestMapping(value = "/firstname/{name}", method=RequestMethod.GET)
	@ResponseBody
	public List<Person> findByFirstName(@PathVariable("name") String name)
	{
		return personRepository.findByFirstName(name);
	}
	
	@RequestMapping(value = "/company/{name}", method=RequestMethod.GET)
	@ResponseBody
	public List<Person> findByCompany(@PathVariable("name") String name)
	{
		return personRepository.findByCompany(name);
	}
	
	@RequestMapping(value = "/email/{name}", method=RequestMethod.GET)
	@ResponseBody
	public List<Person> findByEmail(@PathVariable("name") String email)
	{
		return personRepository.findByEmail(email);
	}
}