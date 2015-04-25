package edu.iis.mto.intergrationtest.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import edu.iis.mto.intergrationtest.utils.ModeUtils;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"org.dreando.testcontext.repository"})
public class PersistenceConfig {
	private static final String SQL_FOLDER_NAME = "sql/";
	private static final String DATA_SCRIPT_FILENAME_SUFFIX = "-data-script.sql";
	private static final String CONTEXT_PACKAGE_NAME = "org.dreando.testcontext.model";
	private static final Logger LOGGER = LoggerFactory.getLogger(PersistenceConfig.class);
    private static final String SQL_SCHEMA_SCRIPT_NAME = "schema-script.sql";
    private static final String SQL_SCHEMA_SCRIPT_PATH = SQL_FOLDER_NAME + SQL_SCHEMA_SCRIPT_NAME;

    @Value("${database.driver}")
    private String databaseDriverClass;
    
	@Value("${database.url}")
    private String databaseUrl;

    @Value("${database.user}")
    private String databaseUser;

    @Value("${database.password}")
    private String databasePassword;

    @Value("${database.hbm2ddlAuto}")
    private String hbm2ddlAuto;

    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        DataSource dataSource = createDataSource();
        DatabasePopulatorUtils.execute(createDatabasePopulator(), dataSource);
        return dataSource;
    }

    private DatabasePopulator createDatabasePopulator() {
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.setContinueOnError(true);

		/*
         * W zaleznosci od wybranego trybu, dodajemy testowy albo developerski
		 * zbior danych
		 */
        databasePopulator.addScripts(new ClassPathResource(SQL_SCHEMA_SCRIPT_PATH),
                new ClassPathResource(SQL_FOLDER_NAME + ModeUtils.getMode().getModeName() + DATA_SCRIPT_FILENAME_SUFFIX));
        return databasePopulator;
    }

    private SimpleDriverDataSource createDataSource() {
        SimpleDriverDataSource simpleDriverDataSource = new SimpleDriverDataSource();
        Class<?> driverClass = getDriverClass();
        simpleDriverDataSource.setDriverClass(org.h2.Driver.class);

		/*
         * Podajemy dane do utworzenia bazy danych, ktore sa wlasciwe dla
		 * naszego trybu. Patrz PropertyPlaceholderConfigurer i
		 * ApplicationConfig
		 */
        simpleDriverDataSource.setUrl(databaseUrl);
        simpleDriverDataSource.setUsername(databaseUser);
        simpleDriverDataSource.setPassword(databasePassword);
        return simpleDriverDataSource;
    }

    private Class<?> getDriverClass() {
		try {
			return Class.forName(databaseDriverClass);
		} catch (ClassNotFoundException e) {
			LOGGER.error("database driver class not found", e);
			return null;
		}
	}

	@Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(getDataSource());
        entityManagerFactoryBean.setPackagesToScan(CONTEXT_PACKAGE_NAME);
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return entityManagerFactoryBean;
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new JpaTransactionManager();
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
