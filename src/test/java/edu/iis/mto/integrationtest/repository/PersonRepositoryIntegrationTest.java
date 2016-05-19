package edu.iis.mto.integrationtest.repository;

import static edu.iis.mto.integrationtest.repository.PersonBuilder.person;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import edu.iis.mto.integrationtest.model.Person;
import org.springframework.beans.factory.annotation.Autowired;


public class PersonRepositoryIntegrationTest extends IntegrationTest {

	@Autowired
	private PersonRepository personRepository;

	@Test
	public void testCanAccessDbAndGetTestData() {
//		List<Person> foundTestPersons = personRepository.findAll();
//		assertEquals(2, foundTestPersons.size());
	}

	@Test
	public void testSaveNewPersonAndCheckIsPersisted() {
//		long count = personRepository.count();
//		personRepository.save(a(person().withId(count + 1)
//				.withFirstName("Roberto").withLastName("Mancini")));
//		assertEquals(count + 1, personRepository.count());
//		assertEquals("Mancini", personRepository.findOne(count + 1)
//				.getLastName());
	}

	private Person a(PersonBuilder builder) {
		return builder.build();
	}

}
