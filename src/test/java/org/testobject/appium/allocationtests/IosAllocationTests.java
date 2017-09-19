package org.testobject.appium.allocationtests;

import org.junit.Test;
import org.junit.experimental.categories.Category;

public class IosAllocationTests {

	@Test
	public void ByDeviceDescriptorIdAndPoolId() {
		new TestBuilder()
				.setDeviceDescriptorId()
				.setPoolId()
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
				.setPlatformVersion()
				.createIOSDriver()
				.test()
				.createResultChecker()
				.checkDeviceName()
				.checkPlatformVersion()
				.checkPlatformName("IOS");
	}

	@Test
	public void ByPlatformVersion() {
		new TestBuilder()
				.setPlatformVersion()
				.createIOSDriver()
				.test()
				.createResultChecker()
				.checkPlatformVersion()
				.checkPlatformName("IOS");
	}

	@Test
	public void ByRegEx() {
		String regEx = "iPhone.*";
		new TestBuilder()
				.setRegEx(regEx)
				.createIOSDriver()
				.test()
				.createResultChecker()
				.checkMatchesRegEx(regEx)
				.checkPlatformName("IOS");
	}

	@Test
	@Category(PrivateDevice.class)
	public void testPrivateDevice() {
		new TestBuilder()
				.setPrivateDeviceOnly()
				.createIOSDriver()
				.test()
				.createResultChecker()
				.checkPrivateDeviceId()
				.checkPlatformName("IOS");
	}

}
