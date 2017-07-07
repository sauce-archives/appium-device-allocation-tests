package org.testobject.appium.android;

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
				.createAndroidDriver()
				.test();
	}

	@Test
	public void ByDeviceName() {
		new TestBuilder()
				.setDeviceName()
				.createAndroidDriver()
				.test();
	}

	@Test
	public void ByDeviceNameAndPlatformVersion() {
		new TestBuilder()
				.setDeviceName()
				.setPlatformVersion()
				.createAndroidDriver()
				.test();
	}

	@Test
	public void ByPlatformVersion() {
		new TestBuilder()
				.setPlatformVersion()
				.createAndroidDriver()
				.test();
	}

	@Test
	@Category(PrivateDevice.class)
	public void testPrivateDevice() {
		new TestBuilder()
				.setPrivateDeviceOnly()
				.createAndroidDriver()
				.test();
	}

}
