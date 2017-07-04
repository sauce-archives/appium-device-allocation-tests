node {
    git 'https://github.com/testobject/appium-device-allocation-tests.git'
    mvnHome = tool 'M3'
    mvn = mvnHome + '/bin/mvn'

    stage('Run') {

		lock(resource: env.DEVICE_DESCRIPTOR_ID) {
			sh "$mvn -Dtest=android/TestA* clean test"
		}

		// current private device on staging
		lock(resource: 'Motorola_Moto_E_2nd_gen_real') {
			sh "$mvn -Dtest=android/*Private* clean test"
		}

	}

}