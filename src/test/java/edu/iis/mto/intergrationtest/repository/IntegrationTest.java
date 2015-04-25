package edu.iis.mto.intergrationtest.repository;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import edu.iis.mto.intergrationtest.config.ApplicationConfig;
import edu.iis.mto.intergrationtest.utils.Mode;
import edu.iis.mto.intergrationtest.utils.ModeUtils;

@ContextConfiguration(classes = { ApplicationConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class IntegrationTest {

	@BeforeClass
	public static void setUpMode() {
		ModeUtils.setMode(Mode.TEST);
	}
}
