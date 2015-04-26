package edu.iis.mto.integrationtest.repository;

import edu.iis.mto.integrationtest.model.Person;

public class PersonBuilder {

	private long id;
	private String fName;
	private String lName;

	public PersonBuilder withId(long id) {
		this.id = id;
		return this;
	}

	public PersonBuilder withFirstName(String fName) {
		this.fName = fName;
		return this;
	}

	public PersonBuilder withLastName(String lName) {
		this.lName = lName;
		return this;
	}

	public Person build() {
		Person person = new Person();
		person.setId(id);
		person.setFirstName(fName);
		person.setLastName(lName);
		return person;

	}

	static PersonBuilder person() {

		return new PersonBuilder();
	}
}
