package edu.iis.mto.integrationtest.repository;

import java.util.List;

import edu.iis.mto.integrationtest.model.Person;


public interface PersonRepository {

	List<Person> findAll();

	long count();

	void save(Person person);

	Person findOne(long id);

}
