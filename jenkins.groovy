node {
    withEnv([]) {

        stage("Build") {
            sh './gradlew build'
        }

        stage("Test") {
            runTestWithTag()
        }

        stage("Allure") {
            generateAllure()
        }
    }
}

def runTestWithTag() {
    try {
        labelledShell(label: "Run", script: "chmod +x gradlew \n./gradlew -x test")
    } finally {
        echo "some failed tests"
    }
}

def generateAllure() {
    allure([
            includeProperties: true,
            jdk              : '',
            properties       : [],
            reportBuildPolicy: 'ALWAYS',
            results          : [[path: 'build/allure-results']]
    ])
}








