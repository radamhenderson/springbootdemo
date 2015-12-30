package com.example;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.application.SampleApplication;
import com.application.model.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SampleApplication.class)
@IntegrationTest("server.port:0")
@WebAppConfiguration
public class SampleApplicationTests {
	

	@Value("${local.server.port}")
	private int port;
	
	 private String getBaseUrl() {
		 return "http://localhost:" + port;
	 }		

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testFindById()
	{
		 
		TestRestTemplate restTemplate = new TestRestTemplate();		
		HttpEntity<String> requestEntity = new HttpEntity<String>(new HttpHeaders());
	
		ResponseEntity<Person> entity = restTemplate.exchange(getBaseUrl() + "person/4", HttpMethod.GET, requestEntity, Person.class);		
		Assert.assertTrue(entity.getBody().getId() == 4);
	}

	@Test
	public void testFindByLastName()
	{
		 
		TestRestTemplate restTemplate = new TestRestTemplate();		
		HttpEntity<String> requestEntity = new HttpEntity<String>(new HttpHeaders());
	
		ResponseEntity<List> entity = restTemplate.exchange(getBaseUrl() + "person/lastname/Relph", HttpMethod.GET, requestEntity, List.class);		
		Assert.assertTrue(entity.getBody().size() == 1);
	}
	
	@Test
	public void testDelete()
	{
		 
		TestRestTemplate restTemplate = new TestRestTemplate();		
		HttpEntity<String> requestEntity = new HttpEntity<String>(new HttpHeaders());

		restTemplate.exchange(getBaseUrl() + "person/4", HttpMethod.DELETE, requestEntity, Person.class);
		
		ResponseEntity<Person> entity = restTemplate.exchange(getBaseUrl() + "person/4", HttpMethod.GET, requestEntity, Person.class);		
		Assert.assertTrue(entity.getBody().getId() == null);
	}
	
}
