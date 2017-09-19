package org.testobject.appium.allocationtests;

import io.appium.java_client.AppiumDriver;
import org.testobject.api.TestObjectClient;
import org.testobject.rest.api.model.DeviceDescriptor;

import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;

public class TestResultChecker extends EnvironmentVariables {

	private DeviceDescriptor.DeviceContainer device;

	public TestResultChecker(AppiumDriver driver) {
		int reportId = Integer.valueOf(driver.getCapabilities().getCapability("testobject_test_report_id").toString());
		String userId = driver.getCapabilities().getCapability("testobject_user_id").toString();
		String projectId = driver.getCapabilities().getCapability("testobject_project_id").toString();

		TestObjectClient client = TestObjectClient.Factory.create(REST_API);
		client.login(userId, PASSWORD);
		device = client.getTestReport(userId, projectId, reportId).getDevice();
	}

	public TestResultChecker checkDeviceDescriptorId() {
		assertTrue(device.id.equals(DEVICE_DESCRIPTOR_ID));
		return this;
	}

	public TestResultChecker checkDeviceName() {
		assertTrue(Pattern.compile(device.name, Pattern.CASE_INSENSITIVE).matcher(DEVICE_NAME).matches());
		return this;
	}

	public TestResultChecker checkPlatformName() {
		assertTrue(device.os.name().equalsIgnoreCase(PLATFORM_NAME));
		return this;
	}

	public TestResultChecker checkPlatformVersion() {
		assertTrue(device.osVersion.startsWith(DEVICE_PLATFORM_VERSION));
		return this;
	}

	public TestResultChecker checkPrivateDeviceId() {
		assertTrue(device.id.equals(PRIVATE_DEVICE_DESCRIPTOR_ID));
		return this;
	}
}
