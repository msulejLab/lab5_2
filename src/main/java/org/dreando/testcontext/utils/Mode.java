package org.dreando.testcontext.utils;

public enum Mode {
	DEV("dev"), TEST("test");

	private String modeName;

	private Mode(String modeName) {
		this.modeName = modeName;
	}

	public String getModeName() {
		return this.modeName;
	}
}