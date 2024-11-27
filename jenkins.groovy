
node {
    withEnv() {
            stage ("Allure") {
                generateAllure()
        }
    }
}


def runTestWithTag() {
        labelledShell(label: "Run", script: "chmod +x gradlew \n./gradlew -x test")
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








