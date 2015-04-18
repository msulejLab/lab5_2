package org.dreando.testcontext.main;

import org.dreando.testcontext.config.ApplicationConfig;
import org.dreando.testcontext.model.Person;
import org.dreando.testcontext.repository.PersonRepository;
import org.dreando.testcontext.utils.Mode;
import org.dreando.testcontext.utils.ModeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationMain {

    private static ApplicationContext applicationContext;

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationMain.class);

    public static void main(String[] args) {
        /* Ustawiamy tryb dzialania aplikacji na developerski */
        ModeUtils.setMode(Mode.DEV);

        /* Startujemy kontekst springowy i znajdujemy bean reprezentujacy nasze repozytorium */
        applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        PersonRepository repository = applicationContext.getBean(PersonRepository.class);

        /* Znajdujemy rekord dodany jako dane startowe i wyswietlamy go w konsoli */
        Person foundDevPerson = repository.findOne(1L);
        LOGGER.debug("Found person is: {}", foundDevPerson);

        /* Wyswietlamy ilosc wszystkich rekordow */
        long recordsNumber = repository.count();
        LOGGER.debug("Found {} records in total", recordsNumber);
    }
}
