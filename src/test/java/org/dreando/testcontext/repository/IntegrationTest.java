package org.dreando.testcontext.repository;

import org.dreando.testcontext.config.ApplicationConfig;
import org.dreando.testcontext.utils.Mode;
import org.dreando.testcontext.utils.ModeUtils;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@ContextConfiguration(classes = { ApplicationConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
public class IntegrationTest {

	@BeforeClass
	public static void setUpMode() {
		ModeUtils.setMode(Mode.TEST);
	}
}
