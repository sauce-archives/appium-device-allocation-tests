node {
    git 'https://github.com/testobject/appium-device-allocation-tests.git'
    mvnHome = tool 'M3'
    mvn = mvnHome + '/bin/mvn'

    stage('Run') {

		lock(resource: env.DEVICE_DESCRIPTOR_ID) {
			sh "$mvn -Dtest=ios/TestA* clean test"
		}

		// current private device on staging
		lock(resource: 'iPhone_SE_16GB_ios9_real_1') {
			sh "$mvn -Dtest=ios/*Private* clean test"
		}

	}

}