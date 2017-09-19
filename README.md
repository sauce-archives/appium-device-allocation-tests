# appium-device-allocation-tests
This Maven Junit project is for testing device allocation on TestObject platform.

######

### Environment Variables
Required:
* `TESTOBJECT_API_KEY` - api key associated with you TestObject account for Appium test suites.
* `APPIUM_SERVER` - URL that points to the devices hub.
* `REST_API` - URL that points to the rest api.
For other configuration properties refer the source.

### Run
Create two Pipeline Jenkins Items (IOS, Android) and use the Jenkinsfiles to run tests.
Or use the following *Maven commands:* 

- *iOS tests with PrivateDevice category*

`-Dgroups=org.testobject.appium.allocationtests.PrivateDevice -Dtest=Ios* clean test` 

- *iOS tests without PrivateDevice category*

`-DexcludedGroups=org.testobject.appium.allocationtests.PrivateDevice -Dtest=Ios* clean test` 

- *All iOS tests*

`-Dtest=Ios* clean test` 

-----

- *Android tests with PrivateDevice category*

`-Dgroups=org.testobject.appium.allocationtests.PrivateDevice -Dtest=Android* clean test` 

- *Android tests without the PrivateDevice category*

`-DexcludedGroups=org.testobject.appium.allocationtests.PrivateDevice -Dtest=Android* clean test`

- *All Android tests*

`-Dtest=Android* clean test`
