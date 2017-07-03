# appium-device-allocation-tests
This Maven Junit project is for testing device allocation on TestObject platform.

######

## Adding new tests
All Test classes should start with the word `Test`.


## Run
Try to run this project in two separate jenkins tasks (Android, IOS) and use `-Dtest=android/* clean test` maven command to run android tests and use `-Dtest=ios/* clean test` to run iOS ones. .
