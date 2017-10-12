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

public class TestBuilder extends EnvironmentVariables {

	private AppiumDriver driver;
	private DesiredCapabilities capabilities = new DesiredCapabilities();

	public TestBuilder() {
		capabilities.setCapability("testobject_uuid", UUID.randomUUID().toString());
		capabilities.setCapability("testobject_api_key", getEnvOrFail(TESTOBJECT_API_KEY));
		capabilities.setCapability("testobject_appium_version", getEnvOrDefault(TESTOBJECT_APPIUM_VERSION, "1.6.5"));
		capabilities.setCapability("testobject_session_creation_retry", getEnvOrDefault(TESTOBJECT_SESSION_CREATION_RETRY, "1"));
		capabilities.setCapability("testobject_session_creation_timeout",
				getEnvOrDefault(TESTOBJECT_SESSION_CREATION_TIMEOUT, "300000")); //5 minutes
	}

	public TestBuilder setDeviceDescriptorId() {
		capabilities.setCapability("testobject_device", DEVICE_DESCRIPTOR_ID);
		return this;
	}

	public TestBuilder setDeviceName() {
		capabilities.setCapability("deviceName", DEVICE_NAME);
		return this;
	}

	public TestBuilder setPlatformVersion() {
		capabilities.setCapability("platformVersion", PLATFORM_VERSION);
		return this;
	}

	public TestBuilder setPlatformName(String platformName) {
		capabilities.setCapability("platformName", platformName);
		return this;
	}

	public TestBuilder setPoolId() {
		capabilities.setCapability("testobject_pool_id", TESTOBJECT_POOL_ID);
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

	public TestBuilder setRegEx(String regEx) {
		capabilities.setCapability("deviceName", regEx);
		return this;
	}

	public TestBuilder createAndroidDriver() {
		System.out.println("\n" + capabilities.toString());
		Instant startTime = Instant.now();
		try {
			driver = new AndroidDriver(new URL(getEnvOrFail(APPIUM_SERVER)), capabilities);
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
		try {
			driver = new IOSDriver(new URL(getEnvOrFail(APPIUM_SERVER)), capabilities);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		Instant endTime = Instant.now();
		System.out.println("Allocation time: " + startTime.until(endTime, ChronoUnit.SECONDS) + "s");
		return this;
	}

	public TestBuilder test() {
		System.out.println("Context is " + driver.getContext());
		closeConnection();
		return this;
	}

	private void closeConnection() {
		Instant startTime = Instant.now();
		if (driver != null) {
			driver.quit();
		}
		Instant endTime = Instant.now();
		System.out.println("Closing connection time: " + startTime.until(endTime, ChronoUnit.SECONDS) + "s");
		System.out.println(driver.getCapabilities().getCapability("testobject_test_report_url"));
	}

	public TestResultChecker createResultChecker() {
		return new TestResultChecker(driver);
	}
}
