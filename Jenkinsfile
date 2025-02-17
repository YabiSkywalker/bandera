pipeline {
    agent any

    environment {
        GIT_CREDENTIALS_ID = 'YabiSkywalker'
        DOCKER_CREDENTIALS_ID = 'yabiskywalker'
        JAVA_HOME = '/usr/lib/jvm/java-17-openjdk-amd64'
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: "main", url: "https://github.com/YabiSkywalker/bandera.git"
            }
        }

        stage('Clean Package') {
            steps {
                sh "mvn clean package -DskipTests"
            }
        }

        stage('Build') {
            steps {
                sh "docker buildx build --platform=linux/amd64 -t bandera-svc ."
            }
        }

        stage('Tag') {
            steps {
                sh "docker tag bandera-svc yabiskywalker/bandera-service-images:v1.01 "
            }
        }

        stage('Push Image') {
            steps {
                sh "docker push yabiskywalker/bandera-service-images:v1.01"
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