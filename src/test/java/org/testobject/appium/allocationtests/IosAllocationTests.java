package org.testobject.appium.allocationtests;

import org.junit.Test;
import org.junit.experimental.categories.Category;

public class IosAllocationTests {

	@Test
	public void ByDeviceDescriptorIdAndPoolId() {
		new TestBuilder()
				.setDeviceDescriptorId()
				.setPoolId()
				.setAutomationNameToXCUITest()
				.createIOSDriver()
				.test()
				.createResultChecker()
				.checkDeviceDescriptorId()
				.checkPlatformName("IOS");
	}

	@Test
	public void ByDeviceName() {
		new TestBuilder()
				.setDeviceName()
				.setAutomationNameToXCUITest()
				.createIOSDriver()
				.test()
				.createResultChecker()
				.checkDeviceName()
				.checkPlatformName("IOS");
	}

	@Test
	public void ByDeviceNameAndPlatformVersion() {
		new TestBuilder()
				.setDeviceName()
				.setAutomationNameToXCUITest()
				.createIOSDriver()
				.test()
				.createResultChecker()
				.checkDeviceName()
				.checkPlatformName("IOS");
	}

	@Test
	public void ByPlatformVersion() {
		new TestBuilder()
				.setPlatformVersion()
				.setAutomationNameToXCUITest()
				.createIOSDriver()
				.test()
				.createResultChecker()
				.checkPlatformVersion()
				.checkPlatformName("IOS");
	}

	@Test
	public void ByRegExAndPlatformVersion() {
		String regEx = "iPhone.*";
		new TestBuilder()
				.setRegEx(regEx)
				.setAutomationNameToXCUITest()
				.setPlatformVersion()
				.createIOSDriver()
				.test()
				.createResultChecker()
				.checkPlatformVersion()
				.checkMatchesRegEx(regEx)
				.checkPlatformName("IOS");
	}

	@Test
	@Category(PrivateDevice.class)
	public void testPrivateDeviceWithPlatformVersion() {
		new TestBuilder()
				.setPrivateDeviceOnly()
				.setAutomationNameToXCUITest()
				.setPlatformVersion()
				.createIOSDriver()
				.test()
				.createResultChecker()
				.checkPlatformVersion()
				.checkPrivateDeviceId()
				.checkPlatformName("IOS");
	}

}
