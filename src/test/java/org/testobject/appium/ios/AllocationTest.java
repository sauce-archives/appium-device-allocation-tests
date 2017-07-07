package org.testobject.appium.ios;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.testobject.appium.PrivateDevice;
import org.testobject.appium.TestBuilder;

public class AllocationTest {

	@Test
	public void ByDeviceDescriptorIdAndPoolId() {
		new TestBuilder()
				.setDeviceDescriptorId()
				.setPoolId()
				.createIOSDriver()
				.test();
	}

	@Test
	public void ByDeviceName() {
		new TestBuilder()
				.setDeviceName()
				.createIOSDriver()
				.test();
	}

	@Test
	public void ByDeviceNameAndPlatformVersion() {
		new TestBuilder()
				.setDeviceName()
				.setPlatformVersion()
				.createIOSDriver()
				.test();
	}

	@Test
	public void ByPlatformVersion() {
		new TestBuilder()
				.setPlatformVersion()
				.createIOSDriver()
				.test();
	}

	@Test
	@Category(PrivateDevice.class)
	public void testPrivateDevice() {
		new TestBuilder()
				.setPrivateDeviceOnly()
				.createIOSDriver()
				.test();
	}

}
