package edu.iis.mto.integrationtest.utils;

public final class ModeUtils {

	private static final String RUNNING_MODE_SYSTEM_KEY = "running-mode";

	public static void setMode(Mode mode) {
		System.setProperty(RUNNING_MODE_SYSTEM_KEY, mode.getModeName());
	}

	public static Mode getMode() {
		return Mode.valueOf(System.getProperty(RUNNING_MODE_SYSTEM_KEY).toUpperCase());
	}
}
