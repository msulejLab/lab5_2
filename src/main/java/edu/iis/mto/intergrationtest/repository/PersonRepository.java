package edu.iis.mto.intergrationtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.iis.mto.intergrationtest.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
