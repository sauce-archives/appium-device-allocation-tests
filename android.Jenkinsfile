node {
    git 'https://github.com/testobject/appium-device-allocation-tests.git'
    mvnHome = tool 'M3'
    mvn = mvnHome + '/bin/mvn'

    stage('Run') {

		parallel (
			publicDevice: {
				lock(resource: env.DEVICE_DESCRIPTOR_ID) {
					sh "$mvn -DexcludedGroups=org.testobject.appium.allocationtests.PrivateDevice -Dtest=Android* -q clean test"
				}
			},
			privateDevice: {
				lock(resource: env.PRIVATE_DEVICE_DESCRIPTOR_ID) {
                	sh "$mvn -Dgroups=org.testobject.appium.allocationtests.PrivateDevice -Dtest=Android* -q clean test"
                }
			}
		)

	}

}
