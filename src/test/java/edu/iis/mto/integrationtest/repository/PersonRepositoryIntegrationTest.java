package edu.iis.mto.integrationtest.repository;

import static edu.iis.mto.integrationtest.repository.PersonBuilder.person;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.hamcrest.core.IsNull;
import org.junit.Test;

import edu.iis.mto.integrationtest.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PersonRepositoryIntegrationTest extends IntegrationTest {

	@Autowired
	private PersonRepository personRepository;


	@Test
	public void testCanAccessDbAndGetTestData() {
		List<Person> foundTestPersons = personRepository.findAll();
		assertEquals(2, foundTestPersons.size());
	}

	@Test
	public void testSaveNewPersonAndCheckIsPersisted() {
		long count = personRepository.count();
		personRepository.save(a(person().withId(count + 1)
				.withFirstName("Roberto").withLastName("Mancini")));
		assertEquals(count + 1, personRepository.count());
		assertEquals("Mancini", personRepository.findOne(count + 1)
				.getLastName());
	}


	@Test
	public void testReadSinglePerson() {
		Person person = personRepository.findOne(1L);

		assertThat(person.getId(), is(1L));
	}

    @Test
    public void testUpdatePerson() {
        long beforeUpdateCount = personRepository.count();

        Person person = personRepository.findOne(1L);
        person.setFirstName("John");

        personRepository.save(person);

        long afterUpdateCount = personRepository.count();

        assertThat(beforeUpdateCount, is(afterUpdateCount));
        assertThat(personRepository.findOne(1L).getFirstName(), is("John"));
    }

    @Test
    public void testDeletePerson() {
        long beforeDeleteCount = personRepository.count();

        Person person = personRepository.findOne(1L);
        personRepository.delete(person);

        long afterDeleteCount = personRepository.count();

        assertThat(afterDeleteCount, is(beforeDeleteCount - 1));
        assertThat(personRepository.findOne(1L), is(nullValue()));
    }


	private Person a(PersonBuilder builder) {
		return builder.build();
	}
}
