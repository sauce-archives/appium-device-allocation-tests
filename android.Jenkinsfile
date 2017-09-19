#!groovy

def runTest() {
    node {
		stage("checkout") {
			checkout scm
		}
        stage("test") {
        	docker.image("maven:3.5").inside("-v $HOME/.m2:/root/.m2") {
				try {
					sh "mvn -DexcludedGroups=org.testobject.appium.allocationtests.PrivateDevice -Dtest=Android* -q clean test"
				} finally {
					junit "**/target/surefire-reports/*.xml"
				}
            }
        }
    }
}

def runPrivateTest() {
    node {
    	stage("checkout") {
    		checkout scm
    	}
        stage("private test") {
        	docker.image("maven:3.5").inside {
				try {
					sh "mvn -Dgroups=org.testobject.appium.allocationtests.PrivateDevice -Dtest=Android* -q clean test"
				} finally {
					junit "**/target/surefire-reports/*.xml"
				}
            }
        }
    }
}

if (env.APPIUM_SERVER.contains("staging.testobject.org")) {
    lock (resource: env.DEVICE_DESCRIPTOR_ID) {
    	runTest()
	}
	lock(resource: env.PRIVATE_DEVICE_DESCRIPTOR_ID) {
		runPrivateTest()
    }
} else {
    try {
        runTest()
        runPrivateTest()
        if (env.SUCCESS_NOTIFICATION_ENABLED) {
            slackSend channel: "#${env.SLACK_CHANNEL}", color: "good", message: "`${env.JOB_BASE_NAME}` passed (<${BUILD_URL}|open>)", teamDomain: "${env.SLACK_SUBDOMAIN}", token: "${env.SLACK_TOKEN}"
        }
    } catch (err) {
        if (env.APPIUM_SERVER.contains("testobject.com") || env.FAILURE_NOTIFICATION_ENABLED) {
            slackSend channel: "#${env.SLACK_CHANNEL}", color: "bad", message: "`${env.JOB_BASE_NAME}` failed: $err (<${BUILD_URL}|open>)", teamDomain: "${env.SLACK_SUBDOMAIN}", token: "${env.SLACK_TOKEN}"
        }
        throw err
    }
}
