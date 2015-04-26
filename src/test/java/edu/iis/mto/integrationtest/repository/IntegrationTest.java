package edu.iis.mto.integrationtest.repository;

import org.junit.BeforeClass;

import edu.iis.mto.integrationtest.utils.Mode;
import edu.iis.mto.integrationtest.utils.ModeUtils;

public class IntegrationTest {

	@BeforeClass
	public static void setUpMode() {
		ModeUtils.setMode(Mode.TEST);
	}
}
