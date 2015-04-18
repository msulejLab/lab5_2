package org.dreando.testcontext.main;

import org.dreando.testcontext.config.ApplicationConfig;
import org.dreando.testcontext.model.Person;
import org.dreando.testcontext.repository.PersonRepository;
import org.dreando.testcontext.utils.Mode;
import org.dreando.testcontext.utils.ModeUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationMain {

	private static ApplicationContext applicationContext;

	public static void main(String[] args) {
		ModeUtils.setMode(Mode.DEV);
		applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		PersonRepository repository = applicationContext.getBean(PersonRepository.class);

		Person foundDevPerson = repository.findOne(1L);
		System.out.println(foundDevPerson);
	}
}
