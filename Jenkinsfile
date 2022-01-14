pipeline {
  environment {
    registry = "justiceofpeace/restaurant_app"
    registryCredential = 'docker-hub-credentials'
    dockerImage = ''
  }
  agent any
  tools {
    maven 'M3'
  }
  stages {
    stage('Compile & Build ') {
      steps {
        sh 'mvn -B -DskipTests clean package'
      }
    }
    stage('Building Docker Image') {
      steps{
        script {
          dockerImage = docker.build registry + ":$BUILD_NUMBER"
        }
      }
    }
    stage('Push Image To Docker Hub') {
      steps{
        script {
              docker.withRegistry('https://registry.hub.docker.com', 'docker-hub-credentials') {
              dockerImage.push("${env.BUILD_NUMBER}")
              dockerImage.push("latest")
          }
        }
      }
    }
  
  }
}
