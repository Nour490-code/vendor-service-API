pipeline {
    agent any

    tools {
        maven "M3"   // 👈 Match this to the name in Jenkins Global Tool Configuration
        jdk "jdk"        // 👈 Match this to your installed JDK
    }

    environment {
    SONAR_TOKEN = credentials('sonarcloud-token')
    DOCKER_TOKEN = credentials('docker-token')
    EC2_IP = credentials('aws-ec2-ip')
    MONGO_ADMIN = credentials('mongo-admin')
    MONGO_PASSWORD = credentials('mongo-pass')
    MONGO_CLUSTER = credentials('mongo-cluster')
    MONGO_DATABASE = credentials('mongo-db')
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
         stage('Approval to Continue') {
                    steps {
                        script {
                            def proceed = input message: 'SonarCloud analysis complete. Do you want to continue?', ok: 'Yes'
                            echo "User chose to continue: ${proceed}"
                        }
                    }
         }
        stage('Docker Build & Push') {
            steps {
                    script {
                        def imageName = "nourghazy/vendor-service-api:latest"

                        sh "docker build -t $imageName ."

                        sh '''
                            echo "$DOCKER_TOKEN" | docker login -u "nourghazy" --password-stdin
                            docker push ''' + imageName + ''' | \
                            grep -E 'Pushing|Layer already exists|Mounted from' --line-buffered
                        '''
                    }
            }
        }
        stage('Deploy To AWS EC2') {
            steps {
                script {
                    sshagent(['ec2-key']) {
                        sh """
                            ssh -o StrictHostKeyChecking=no ec2-user@$EC2_IP '
                                docker pull nourghazy/vendor-service-api:latest &&
                                docker rm -f vendor-container || true &&
                                docker run -d -p 8081:8081 --name vendor-container -e MONGO_ADMIN=$MONGO_ADMIN -e MONGO_PASSWORD=$MONGO_PASSWORD -e MONGO_CLUSTER=$MONGO_CLUSTER -e MONGO_DATABASE=$MONGO_DATABASE nourghazy/vendor-service-api:latest
                            '
                        """
                    }
                }
            }
        }
    }
}