pipeline {
    agent any

    environment {
        BASE_GIT_URL = 'https://github.com/DimaKarpuk/javaLess.git'
        BRANCH = 'main' // Замените на нужную ветку
    }

    stages {
        stage('Checkout') {
            steps {
                script {
                    // Проверка и загрузка кода из репозитория
                    checkout([
                            $class: 'GitSCM',
                            branches: [[name: BRANCH]],
                            userRemoteConfigs: [[url: BASE_GIT_URL]]
                    ])
                }
            }
        }

        stage('Build') {
            steps {
                script {
                    // Сборка проекта
                    sh './gradlew build'
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    // Запуск тестов
                    sh './gradlew test'
                }
            }
        }

        stage('Allure Report') {
            steps {
                allure([
                        includeProperties: true,
                        jdk: '',
                        results: [[path: 'build/allure-results']]
                ])
            }
        }
    }

    post {
        always {
            script {
                // Очистка рабочей директории
                sh './gradlew clean'
            }
        }

        success {
            script {
                echo 'Build and tests succeeded!'
            }
        }

        failure {
            script {
                echo 'Build or tests failed!'
            }
        }
    }
}




