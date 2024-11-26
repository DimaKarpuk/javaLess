pipeline {
    agent any
    stages {
        stage('Clone git repo') {
            steps {
                git url: 'https://github.com/DimaKarpuk/javaLess.git', branch: 'master', changelog: true
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    dockerImage = docker.build("my-docker-image")
                }
            }
        }
        stage('Run Docker Container') {
            steps {
                script {
                    dockerImage.inside {
                        bat 'gradle clean test'
                        bat 'gradle allureReport'
                    }
                }
            }
        }
        stage('Allure report') {
            steps{
                bat 'allure generate /oz.by/build/allure-results --clean -o /simpleTest/build/allure-report'
            }
        }
    }
    post {
        always {
            allure includeProperties: false, jdk: '', results: [[path: 'build/allure-results']]
        }
    }
}