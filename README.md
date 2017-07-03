# appium-device-allocation-tests
This Maven Junit project is for testing device allocation on TestObject platform.

######

### Adding new tests
All Test classes should start with the word `Test`.

### Environment Variables
* `TESTOBJECT_API_KEY`
* `DEVICE_DESCRIPTOR_ID`
* `TESTOBJECT_POOL_ID`
* `DEVICE_NAME`
* `DEVICE_PLATFORM_VERSION`
* `APPIUM_SERVER`

### Run
Try to run this project in two separate jenkins tasks (Android, IOS) and use `-Dtest=android/* clean test` maven command for Android and `-Dtest=ios/* clean test` for iOS.
