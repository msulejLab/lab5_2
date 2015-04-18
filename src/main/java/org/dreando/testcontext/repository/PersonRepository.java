package org.dreando.testcontext.repository;

import org.dreando.testcontext.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
