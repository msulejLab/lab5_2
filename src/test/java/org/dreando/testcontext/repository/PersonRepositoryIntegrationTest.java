package org.dreando.testcontext.repository;

import org.dreando.testcontext.model.Person;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
        /* Warto zanotowac, ze istnieje cos takiego jak generator sekwencji, ktory odpowiednio skonfigurowany
         * przejmie za nas role martwienia sie o unikalne i nastepujace po sobie identyfikatory. Patrz @SequenceGenerator */
        personRepository.save(getPerson(count + 1, "Roberto", "Mancini"));
        assertEquals(count + 1, personRepository.count());
        assertEquals("Mancini", personRepository.findOne(count + 1).getLastName());
    }

    private Person getPerson(Long id, String firstName, String lastName) {
        Person person = new Person();
        person.setId(id);
        person.setFirstName(firstName);
        person.setLastName(lastName);
        return person;
    }
}
