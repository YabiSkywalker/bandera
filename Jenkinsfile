pipeline {
    agent any

    environment {
        GIT_CREDENTIALS_ID = 'YabiSkywalker'
        DOCKER_CREDENTIALS_ID = 'JenkinsToDocker'
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

        stage('Authenticate Docker') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: env.DOCKER_CREDENTIALS_ID,
                    usernameVariable: 'DOCKER_HUB_USER',
                    passwordVariable: 'DOCKER_HUB_PASSWORD'
                )]) {
                    sh """echo ${DOCKER_HUB_PASSWORD} | docker login -u ${DOCKER_HUB_USER} --password-stdin"""
                }
            }
        }

        stage('Docker Push') {
            steps {
                sh "docker push yabiskywalker/bandera-service-images:v1.01"
            }
        }

        stage('reDeploy') {
            steps {
                sh '''
                    curl -X POST "https://app.harness.io/gateway/pipeline/api/pipeline/execute/RollingDeploy?accountIdentifier=kQsoKw8wQV6hD_BlSuffZA&orgIdentifier=default&projectIdentifier=default_project" \\
                    -H "Content-Type: application/yaml" \\
                    -H "x-api-key: pat.kQsoKw8wQV6hD_BlSuffZA.67b4ff61f58a04569cf6a0f5.ZzIXq5URfWz2gMBLHgHY" \\
                    -d $'
                    pipeline:
                      identifier: "RollingDeploy"
                      stages:
                        - stage:
                            identifier: "Deploy"
                            type: "Deployment"
                            spec:
                              service:
                                serviceInputs:
                                  serviceDefinition:
                                    spec:
                                      artifacts:
                                        primary:
                                          sources:
                                            - identifier: "Primary"
                                              spec:
                                                tag: "v1.01"
                    '
                '''
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




curl -X POST "https://app.harness.io/gateway/pipeline/api/pipeline/execute/RollingDeploy?accountIdentifier=kQsoKw8wQV6hD_BlSuffZA&orgIdentifier=default&projectIdentifier=default_project" \
  -H "Content-Type: application/yaml" \
  -H "x-api-key: pat.kQsoKw8wQV6hD_BlSuffZA.67b4ff61f58a04569cf6a0f5.ZzIXq5URfWz2gMBLHgHY" \
  -d '
pipeline:
  identifier: "RollingDeploy"
  stages:
    - stage:
        identifier: "Deploy"
        type: "Deployment"
        spec:
          service:
            serviceInputs:
              serviceDefinition:
                spec:
                  artifacts:
                    primary:
                      sources:
                        - identifier: "Primary"
                          spec:
                            tag: "v1.0"




