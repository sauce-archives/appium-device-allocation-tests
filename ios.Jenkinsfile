node {
    git 'https://github.com/testobject/appium-device-allocation-tests.git'
    mvnHome = tool 'M3'
    mvn = mvnHome + '/bin/mvn'

    stage('Run') {

		lock(resource: env.DEVICE_DESCRIPTOR_ID) {
			sh "$mvn -DexcludedGroups=org.testobject.appium.allocationtests.PrivateDevice -Dtest=Ios* clean test"
		}

		lock(resource: env.PRIVATE_DEVICE_DESCRIPTOR_ID) {
			sh "$mvn -Dgroups=org.testobject.appium.allocationtests.PrivateDevice -Dtest=Ios* clean test"
		}

	}

}