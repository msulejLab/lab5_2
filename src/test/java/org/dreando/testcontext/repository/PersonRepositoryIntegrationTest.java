package org.dreando.testcontext.repository;

import static org.junit.Assert.assertEquals;

import org.dreando.testcontext.model.Person;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PersonRepositoryIntegrationTest extends IntegrationTest {

	@Autowired
	private PersonRepository personRepository;

	@Test
	public void testCanAccessDbAndGetTestData() {
		Person foundTestPerson = personRepository.findOne(1L);
		assertEquals("Testowy", foundTestPerson.getLastName());
	}

	@Test
	public void testWriteNewRecordAndCount() {
		personRepository.saveAndFlush(getPerson());
		System.out.println(personRepository.findAll());
		assertEquals(2, personRepository.count());
	}

	private Person getPerson() {
		Person person = new Person();
		person.setId(2L);
		person.setFirstName("Marian");
		person.setLastName("Testowy2");
		return person;
	}

}
