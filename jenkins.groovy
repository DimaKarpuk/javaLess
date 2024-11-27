pipeline {
    agent any
    environment {
        ALLURE_RESULTS_DIR = 'build/allure-results'
    }

    stages {
        stage('Checkout Branch') {
            steps {
                script {
                    if (!"${branch_cutted}".contains("master")) {
                        try {
                            getProject("${base_git_url}", "${branch_cutted}")
                        } catch (err) {
                            echo "Failed get branch ${branch_cutted}"
                            throw ("${err}")
                        }
                    } else {
                        echo "Current branch is master"
                    }
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    try {
                        parallel getTestStages(["test"])
                    } finally {
                        stage("Allure") {
                            generateAllure()
                        }
                    }
                }
            }
        }
    }
}

def getTestStages(testTags) {
    def stages = [:]
    testTags.each { tag ->
        stages["${tag}"] = {
            runTestWithTag(tag)
        }
    }
    return stages
}

def runTestWithTag(String tag) {
    try {
        labelledShell(label: "Run ${tag}", script: """
            chmod +x gradlew
            ./gradlew -x test ${tag}
            ./gradlew allureReport
        """)
    } finally {
        echo "some failed tests"
    }
}

def getProject(String repo, String branch) {
    cleanWs()
    checkout scm: [
            $class           : 'GitSCM',
            branches         : [[name: branch]],
            userRemoteConfigs: [[url: repo]]
    ]
}

def generateAllure() {
    allure([
            includeProperties: true,
            jdk              : '',
            properties       : [],
            reportBuildPolicy: 'ALWAYS',
            results          : [[path: "${env.ALLURE_RESULTS_DIR}"]]
    ])
}



