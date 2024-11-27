node {
    withEnv([]) {
        stage("Checkout") {
            checkout scm
        }

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
    labelledShell(label: "Run", script: """
        chmod +x gradlew
        ./gradlew test
    """)
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








