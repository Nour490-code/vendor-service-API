pipeline {
    agent any

    tools {
        maven "M3"   // 👈 Match this to the name in Jenkins Global Tool Configuration
        jdk "jdk"        // 👈 Match this to your installed JDK
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

        stage('Post-Build') {
            steps {
                echo '✅ Maven build completed successfully.'
            }
        }
    }
}
