#!groovy

try {
    runTest()
    if (env.SUCCESS_NOTIFICATION_ENABLED) {
        slackSend channel: "#${env.SLACK_CHANNEL}", color: "good", message: "`${env.JOB_BASE_NAME}` passed (<${BUILD_URL}|open>)", teamDomain: "${env.SLACK_SUBDOMAIN}", token: "${env.SLACK_TOKEN}"
    }
} catch (err) {
    if (isProduction() || env.FAILURE_NOTIFICATION_ENABLED) {
        slackSend channel: "#${env.SLACK_CHANNEL}", color: "bad", message: "`${env.JOB_BASE_NAME}` failed: $err (<${BUILD_URL}|open>)", teamDomain: "${env.SLACK_SUBDOMAIN}", token: "${env.SLACK_TOKEN}"
    }
	if (currentBuild.result == null || currentBuild.result == "UNSTABLE") {
		currentBuild.result = "FAILURE"
	}
    throw err
} finally {
    reportResultsToInfluxDb()
}

def runTest() {
    node {
        stage("checkout") {
            checkout scm
        }
        stage("test") {
            docker.image("maven:3.5").inside("-v $HOME/.m2:/root/.m2") {
                try {
                    if (env.TESTS) {
                        sh "mvn -Dtest=${env.TESTS} -q clean test"
                    }
                } finally {
                    junit "**/target/surefire-reports/*.xml"
                }
            }
        }
    }
}

def reportResultsToInfluxDb() {
    if (env.REPORT_RESULTS.is("true")) {
        node {
            def influxDb
            if (env.INFLUX_DB) {
                influxDb = env.INFLUX_DB
            } else {
                influxDb = isProduction() ? "production" : "staging"
            }
            def result = 0
            if (currentBuild.result == null) {
                currentBuild.result = "SUCCESS"
                result = 1
            }

            def customData = ['result': result]
            if (isProduction()) {
                customData = env.PART_OF_SLA.is("true") ? ['result': result, 'sla': true] : ['result': result, 'sla': false]
            }
            step([$class       : 'InfluxDbPublisher',
                  customData   : customData,
                  customDataMap: null,
                  customPrefix : null,
                  target       : influxDb])
        }
    }
}

def isProduction() {
    return env.APPIUM_URL && env.APPIUM_URL.contains("testobject.com")
}
