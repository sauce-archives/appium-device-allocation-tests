node {
    git 'https://github.com/testobject/appium-device-allocation-tests.git'
    mvnHome = tool 'M3'
    mvn = mvnHome + '/bin/mvn'
    def staging = env.APPIUM_SERVER.contains("staging.testobject.org") ? true : false

    stage ('Run') {

    	if (staging) {
			lock(resource: env.DEVICE_DESCRIPTOR_ID) {
				sh "$mvn -DexcludedGroups=org.testobject.appium.allocationtests.PrivateDevice -Dtest=Android* -q clean test"
			}

			lock(resource: env.PRIVATE_DEVICE_DESCRIPTOR_ID) {
				sh "$mvn -Dgroups=org.testobject.appium.allocationtests.PrivateDevice -Dtest=Android* -q clean test"
        	}
        } else {
        	sh "$mvn -Dtest=Ios* -q clean test"
        }

	}
}
