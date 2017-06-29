# appium-device-allocation-tests
This project is for testing device allocation on TestObject platform

######

#### Adding new tests

All Test classes should start with either `TestAndroid` or `TestIOS`.


## Run

Run this project in two separate jenkins tasks (Android, IOS).


#### Android

Run Android using maven command `-Dtest=TestAndroid* clean test`


#### IOS

Run Android using maven command `-Dtest=TestIOS* clean test`
