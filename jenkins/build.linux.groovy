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
                sh 'docker rm -f comma'
            }
        }

        stage('Build and Run with Docker Compose') {
            steps {
                dir('./comma') {
                    sh 'docker-compose up --build -d'
                }
            }
        }

        stage('Image Remove') {
            steps {
                sh 'docker image prune -f'
            }
        }
    }
}
