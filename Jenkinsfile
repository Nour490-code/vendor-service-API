pipeline {
    agent any

    tools {
        maven "M3"   // 👈 Match this to the name in Jenkins Global Tool Configuration
        jdk "jdk"        // 👈 Match this to your installed JDK
    }

    environment {
    SONAR_TOKEN = credentials('sonarcloud-token')
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Nour490-code/vendor-service-API.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }
        stage('SonarCloud Analysis') {
          steps {
            sh """
              mvn sonar:sonar \
                -Dsonar.projectKey=Nour490-code_vendor-service-API \
                -Dsonar.organization=nour490-code \
                -Dsonar.host.url=https://sonarcloud.io \
                -Dsonar.login=$SONAR_TOKEN
            """
          }
        }
    }
}