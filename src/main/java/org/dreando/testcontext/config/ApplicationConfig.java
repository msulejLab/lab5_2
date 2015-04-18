package org.dreando.testcontext.config;

import org.dreando.testcontext.utils.ModeUtils;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;

@Configuration
@ComponentScan("org.dreando.testcontext")
@Import(value = { PersistenceConfig.class })
public class ApplicationConfig {

	@Bean
	public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
		PropertyPlaceholderConfigurer resolver = new PropertyPlaceholderConfigurer();
		resolver.setLocation(new ClassPathResource(ModeUtils.getMode().getModeName() + "-persistence.properties"));
		return resolver;
	}
}
