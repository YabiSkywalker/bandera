pipeline {
    agent any

    environment {
        GIT_CREDENTIALS_ID = 'YabiSkywalker'
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: "main", url: "https://github.com/YabiSkywalker/bandera.git"
            }
        }

        stage('Clean Package') {
            steps {
                sh "mvn clean package"
            }
        }

        stage('Build') {
            steps {
                sh "docker buildx build --platform=linux/amd64 -t bandera-svc ."
            }
        }
    }

    post {
        success {
            echo "✅ Deployment successful!"
        }
        failure {
            echo "❌ Deployment failed!"
        }
    }
}