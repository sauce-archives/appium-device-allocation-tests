#!groovy

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
                        if (currentBuild.result == null) {
                            currentBuild.result = "SUCCESS" // sets the ordinal as 0 and boolean to true (influxDB)
                        }
                    }
                } catch (ex) {
                    if (currentBuild.result == null) {
                        currentBuild.result = "FAILURE" // sets the ordinal as 4 and boolean to false (influxDB)
                    }
                } finally {
                    junit "**/target/surefire-reports/*.xml"
                    reportResultsToInfluxDb()
                }
            }
        }
    }
}

try {
    runTest()
    if (env.SUCCESS_NOTIFICATION_ENABLED) {
        slackSend channel: "#${env.SLACK_CHANNEL}", color: "good", message: "`${env.JOB_BASE_NAME}` passed (<${BUILD_URL}|open>)", teamDomain: "${env.SLACK_SUBDOMAIN}", token: "${env.SLACK_TOKEN}"
    }
} catch (err) {
    if (isProduction() || env.FAILURE_NOTIFICATION_ENABLED) {
        slackSend channel: "#${env.SLACK_CHANNEL}", color: "bad", message: "`${env.JOB_BASE_NAME}` failed: $err (<${BUILD_URL}|open>)", teamDomain: "${env.SLACK_SUBDOMAIN}", token: "${env.SLACK_TOKEN}"
    }
    throw err
}

def reportResultsToInfluxDb() {
    if (env.REPORT_RESULTS ?: true) {
        def influxDb
        if (env.INFLUX_DB) {
            influxDb = env.INFLUX_DB
        } else {
            influxDb = isProduction() ? "production" : "staging"
        }
        step([$class       : 'InfluxDbPublisher',
              customData   : null,
              customDataMap: null,
              customPrefix : null,
              target       : influxDb])
    }
}

def isProduction() {
    return env.APPIUM_URL && env.APPIUM_URL.contains("testobject.com")
}
