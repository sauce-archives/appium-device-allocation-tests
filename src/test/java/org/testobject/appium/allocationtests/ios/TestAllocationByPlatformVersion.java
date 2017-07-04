package org.testobject.appium.allocationtests.ios;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class TestAllocationByPlatformVersion {

	AppiumDriver driver;

	@Before
	public void setup() throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities();

		capabilities.setCapability("testobject_api_key", System.getenv("TESTOBJECT_API_KEY"));
		capabilities.setCapability("platformVersion", System.getenv("DEVICE_PLATFORM_VERSION"));
		capabilities.setCapability("automationName", "XCUITest");

		System.out.println(capabilities.toString());
		driver = new IOSDriver(new URL(System.getenv("APPIUM_SERVER")), capabilities);
		System.out.println(driver.getCapabilities().getCapability("testobject_test_report_url"));
	}

	@Test
	public void printContext() throws Exception {
		System.out.println("Context is " + driver.getContext());
	}

	@After
	public void tearDown() {
		driver.quit();
	}
}
