pipeline {
    agent any

    stages {
        stage('git clone') {
            steps {
                git branch: 'main', credentialsId: 'gitHub-token', url: 'https://github.com/DonghwanSon1/comma.git'
            }
        }

        stage('Docker Container Stop And Remove') {
            steps {
                bat 'docker rm -f comma-film-service'
            }
        }

        stage('Clean and Build') {
            steps {
                bat './gradlew clean build' // clean 후 build
            }
        }

        stage('Build and Run with Docker Compose') {
            steps {
                dir('./comma') {
                    bat 'docker-compose up -d'
                }
            }
        }

        stage('Image Remove') {
            steps {
                bat 'docker image prune -f'
            }
        }
    }
}
