package org.testobject.appium.allocationtests;

public class EnvironmentVariables {

	public static final String TESTOBJECT_API_KEY = System.getenv("TESTOBJECT_API_KEY");
	public static final String APPIUM_SERVER = System.getenv("APPIUM_SERVER");
	public static final String DEVICE_DESCRIPTOR_ID = System.getenv("DEVICE_DESCRIPTOR_ID");
	public static final String DEVICE_NAME = System.getenv("DEVICE_NAME");
	public static final String PLATFORM_VERSION = System.getenv("PLATFORM_VERSION");
	public static final String TESTOBJECT_POOL_ID = System.getenv("TESTOBJECT_POOL_ID");
	public static final String TESTOBJECT_SESSION_CREATION_TIMEOUT = System.getenv("TESTOBJECT_SESSION_CREATION_TIMEOUT");
	public static final String TESTOBJECT_SESSION_CREATION_RETRY = System.getenv("TESTOBJECT_SESSION_CREATION_RETRY");
	public static final String PASSWORD = System.getenv("PASSWORD");
	public static final String PRIVATE_DEVICE_DESCRIPTOR_ID = System.getenv("PRIVATE_DEVICE_DESCRIPTOR_ID");

	public static final String REST_API = System.getenv("REST_API");



	public static String getEnvOrDefault(String env, String s) {
		if (env == null) {
			return s;
		} else {
			return env;
		}
	}

	public static String getEnvOrFail(String env) {
		if (env == null) {
			throw new RuntimeException("Missing obligatory environment variables. Hint: Make sure you supply at least `TESTOBJECT_API_KEY` and `APPIUM_SERVER`");
		} else {
			return env;
		}
	}
}
