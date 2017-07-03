package ios;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class TestAllocationByDeviceDescriptorIdAndPoolId {

	AppiumDriver driver;

	@Before
	public void setup() throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities();

		capabilities.setCapability("testobject_api_key", System.getenv("TESTOBJECT_API_KEY"));
		capabilities.setCapability("testobject_device", System.getenv("DEVICE_DESCRIPTOR_ID"));
		capabilities.setCapability("testobject_pool_id", System.getenv("TESTOBJECT_POOL_ID"));
		capabilities.setCapability("automationName", "XCUITest");

		driver = new IOSDriver(new URL(System.getenv("APPIUM_SERVER")), capabilities);

		System.out.println(capabilities.toString());
		//System.out.println(driver.getCapabilities().getCapability("testobject_test_report_url"));
		//System.out.println(driver.getCapabilities().getCapability("testobject_test_live_view_url"));
		System.out.println(driver.getCapabilities().getCapability("testobject_device: " + "testobject_device"));
		System.out.println(driver.getCapabilities().getCapability("deviceName: " + "deviceName"));
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
