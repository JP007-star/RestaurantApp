pipeline {
  environment {
    registry = "justiceofpeace/restaurant_app"
    MAVEN_HOME = tool('MAVEN3')
    DOCKER_HOME= tool('docker')
    registryCredential = 'docker-hub-credentials'
    dockerImage = ''
  }
  agent any
  stages {
    stage('Compile & Build ') {
      steps {
        sh "${MAVEN_HOME}/bin/mvn package"
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
              docker.withRegistry('https://registry.hub.docker.com', 'docker-hub-credentials')
          {
              dockerImage.push("${env.BUILD_NUMBER}")
              dockerImage.push("latest")
          }
        }
      }
    }
    stage('Deploy to mysql pods'){
        steps{
            sh 'kubectl apply -f db-deployment.yml'
       }
    }
     stage('Deploy to spring-boot pods'){
        steps{
            sh 'kubectl apply -f app-deployment.yml'
       }
    }
    
  
  
  
  }
}
