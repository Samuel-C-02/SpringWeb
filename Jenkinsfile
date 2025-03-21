pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/Samuel-C-02/SpringWeb.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
    }

    post {
        success {
            echo "Build & Tests Passed!"
        }
        failure {
            echo "Build or Tests Failed!"
        }
    }
}
