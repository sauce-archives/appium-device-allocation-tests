package org.testobject.appium.allocationtests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class TestBuilder {

	private AppiumDriver driver;
	private DesiredCapabilities capabilities = new DesiredCapabilities();

	public TestBuilder() {
		capabilities.setCapability("testobject_uuid", UUID.randomUUID().toString());
		capabilities.setCapability("testobject_api_key", getEnvOrFail("TESTOBJECT_API_KEY"));
		capabilities.setCapability("testobject_session_creation_retry", getEnvOrDefault("TESTOBJECT_SESSION_CREATION_RETRY", "1"));
		capabilities.setCapability("testobject_session_creation_timeout", getEnvOrDefault("TESTOBJECT_SESSION_CREATION_TIMEOUT", "300000")); //5 minutes
	}

	public TestBuilder setDeviceDescriptorId() {
		capabilities.setCapability("testobject_device", System.getenv("DEVICE_DESCRIPTOR_ID"));
		return this;
	}

	public TestBuilder setDeviceName() {
		capabilities.setCapability("deviceName", System.getenv("DEVICE_NAME"));
		return this;
	}

	public TestBuilder setPlatformVersion() {
		capabilities.setCapability("platformVersion", System.getenv("DEVICE_PLATFORM_VERSION"));
		return this;
	}


	public TestBuilder setPlatformName(String platformName) {
		capabilities.setCapability("platformName", platformName);
		return this;
	}

	public TestBuilder setPoolId() {
		capabilities.setCapability("testobject_pool_id", System.getenv("TESTOBJECT_POOL_ID"));
		return this;
	}

	public TestBuilder setPrivateDeviceOnly() {
		capabilities.setCapability("privateDevicesOnly", true);
		return this;
	}

	public TestBuilder setAutomationNameToXCUITest() {
		capabilities.setCapability("automationName", "XCUITest");
		return this;
	}

	public TestBuilder createAndroidDriver() {
		System.out.println(capabilities.toString());
		Instant startTime = Instant.now();
		System.out.println("Began allocation driver at " + startTime);
		try {
			driver = new AndroidDriver(new URL(getEnvOrFail("APPIUM_SERVER")), capabilities);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		Instant endTime = Instant.now();
		System.out.println("Allocation time: " + startTime.until(endTime, ChronoUnit.SECONDS) + "s");
		return this;
	}

	public TestBuilder createIOSDriver() {
		System.out.println(capabilities.toString());
		Instant startTime = Instant.now();
		System.out.println("Began allocation driver at " + startTime);
		try {
			driver = new IOSDriver(new URL(getEnvOrFail("APPIUM_SERVER")), capabilities);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		Instant endTime = Instant.now();
		System.out.println("Allocation time: " + startTime.until(endTime, ChronoUnit.SECONDS) + "s");
		return this;
	}

	public void test() {
		System.out.println("Context is " + driver.getContext());
		closeConnection();
	}

	private void closeConnection() {
		System.out.println(driver.getCapabilities().getCapability("testobject_test_report_url"));
		System.out.println("Calling driver.quit() at " + Instant.now());
		if (driver != null) {
			driver.quit();
		}
		System.out.println("Test finished at " + Instant.now());
	}

	private String getEnvOrDefault(String env, String s) {
		String var = System.getenv(env);
		if (var == null) {
			return s;
		} else {
			return var;
		}
	}

	private String getEnvOrFail(String env) {
		String var = System.getenv(env);
		if (var == null) {
			throw new RuntimeException(env + " is null");
		} else {
			return var;
		}
	}

}
