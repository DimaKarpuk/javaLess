pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "my-java-gradle-app"
        DOCKER_TAG = "latest"
        BASE_GIT_URL = "https://github.com/DimaKarpuk/javaLess.git"
    }

    stages {
        stage('Initialize') {
            steps {
                script {
                    task_branch = "${TEST_BRANCH_NAME}"
                    branch_cutted = task_branch.contains("origin") ? task_branch.split('/')[1] : task_branch.trim()
                    currentBuild.displayName = "$branch_cutted"
                }
            }
        }

        stage('Checkout Branch') {
            steps {
                script {
                    if (!"$branch_cutted".contains("master")) {
                        try {
                            getProject(BASE_GIT_URL, branch_cutted)
                        } catch (err) {
                            echo "Failed to get branch $branch_cutted"
                            throw ("${err}")
                        }
                    } else {
                        echo "Current branch is master"
                    }
                }
            }
        }

        stage('Run Tests') {
            parallel {
                stage('API Tests') {
                    steps {
                        script {
                            runTestWithTag('apiTests')
                        }
                    }
                }
                stage('UI Tests') {
                    steps {
                        script {
                            runTestWithTag('uiTests')
                        }
                    }
                }
            }
        }

        stage('Generate Allure Report') {
            steps {
                script {
                    generateAllure()
                }
            }
        }
    }

    post {
        always {
            echo 'Pipeline completed'
        }
    }
}

def getProject(String repo, String branch) {
    cleanWs()
    checkout scm: [
            $class: 'GitSCM',
            branches: [[name: branch]],
            userRemoteConfigs: [[url: repo]]
    ]
}

def runTestWithTag(String tag) {
    try {
        labelledShell(label: "Run ${tag}", script: """
            cd ${env.WORKSPACE}/javaLess
            chmod +x gradlew
            ./gradlew -x test ${tag}
        """)
    } finally {
        echo "some failed tests"
    }
}

def generateAllure() {
    sh 'cd ${WORKSPACE}/javaLess && allure generate build/allure-results -o build/allure-report'
    allure([
            includeProperties: true,
            jdk: '',
            properties: [],
            reportBuildPolicy: 'ALWAYS',
            results: [[path: 'build/allure-report']]
    ])
}





