pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    docker.build('myTestImage', '.')
                }
            }
        }

        stage('Run Docker Container') {
            steps {
                script {
                    docker.image('myTestImage').inside {
                        sh './gradlew clean test'
                    }
                }
            }
        }
    }

    post {
        always {
            cleanWs()
        }

        success {
            script {
                echo 'Docker build and tests succeeded!'
            }
        }

        failure {
            script {
                echo 'Docker build or tests failed!'
            }
        }
    }
}
