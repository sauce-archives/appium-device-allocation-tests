node {
    stage('Run') {

		lock(resource: env.DEVICE_DESCRIPTOR_ID) {
			sh "mvn -Dtest=ios/TestA* -DexcludedGroups=org.testobject.appium.allocationtests.PrivateDevice clean test"
		}

		// current private device on staging
		lock(resource: 'iPhone_SE_16GB_ios9_real_1') {
			sh "$mvn -Dtest=ios/*Private* clean test"
		}

	}

}