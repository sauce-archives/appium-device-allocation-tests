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
Create two Pipeline Jenkins Items (IOS, Android) and use the Jenkinsfiles to run tests.
Or use the following *Maven commands:* 

- *iOS tests with PrivateDevice category*

`-Dgroups=org.testobject.appium.PrivateDevice -Dtest=ios/* clean test` 

- *iOS tests without PrivateDevice category*

`-DexcludedGroups=org.testobject.appium.PrivateDevice -Dtest=ios/* clean test` 

- *All iOS tests*

`-Dtest=ios/* clean test` 

-----

- *Android tests with PrivateDevice category*

`-Dgroups=org.testobject.appium.PrivateDevice -Dtest=android/* clean test` 

- *Android tests without the PrivateDevice category*

`-DexcludedGroups=org.testobject.appium.PrivateDevice -Dtest=android/* clean test`

- *All Android tests*

`-Dtest=android/* clean test`
