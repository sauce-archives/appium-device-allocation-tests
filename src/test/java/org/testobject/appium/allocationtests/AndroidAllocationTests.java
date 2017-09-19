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
				.checkPlatformName();
	}

	@Test
	public void ByDeviceName() {
		new TestBuilder()
				.setDeviceName()
				.createAndroidDriver()
				.test()
				.createResultChecker()
				.checkDeviceName()
				.checkPlatformName();
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
				.checkPlatformName();
	}

	@Test
	public void ByPlatformVersion() {
		new TestBuilder()
				.setPlatformVersion()
				.createAndroidDriver()
				.test()
				.createResultChecker()
				.checkPlatformVersion()
				.checkPlatformName();
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
				.checkPlatformName();
	}

}
