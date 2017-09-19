package org.testobject.appium.allocationtests;

import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AndroidAllocationTests {

	@Test
	public void ByDeviceDescriptorIdAndPoolId() {
		new TestBuilder()
				.setDeviceDescriptorId()
				.setPoolId()
				.createAndroidDriver()
				.test()
				.createResultChecker()
				.checkDeviceDescriptorId()
				.checkPlatformName("ANDROID");
	}

	@Test
	public void ByDeviceName() {
		new TestBuilder()
				.setDeviceName()
				.createAndroidDriver()
				.test()
				.createResultChecker()
				.checkDeviceName()
				.checkPlatformName("ANDROID");
	}

	@Test
	public void ByDeviceNameAndPlatformVersion() {
		new TestBuilder()
				.setDeviceName()
				.setPlatformVersion()
				.createAndroidDriver()
				.test()
				.createResultChecker()
				.checkDeviceName()
				.checkPlatformVersion()
				.checkPlatformName("ANDROID");
	}

	@Test
	public void ByPlatformVersion() {
		new TestBuilder()
				.setPlatformVersion()
				.createAndroidDriver()
				.test()
				.createResultChecker()
				.checkPlatformVersion()
				.checkPlatformName("ANDROID");
	}

	@Test
	public void ByRegEx() {
		String regEx = "LG.*";
		new TestBuilder()
				.setRegEx(regEx)
				.createAndroidDriver()
				.test()
				.createResultChecker()
				.checkMatchesRegEx(regEx)
				.checkPlatformName("ANDROID");
	}

	@Test
	@Category(PrivateDevice.class)
	public void testPrivateDevice() {
		new TestBuilder()
				.setPrivateDeviceOnly()
				.createAndroidDriver()
				.test()
				.createResultChecker()
				.checkPrivateDeviceId()
				.checkPlatformName("ANDROID");
	}

}
