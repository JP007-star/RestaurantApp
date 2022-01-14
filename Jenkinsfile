pipeline {
  environment {
    registry = "justiceofpeace/restaurant_app"
    registryCredential = 'docker-hub-credentials'
    dockerImage = ''
  }
 agent any
  stages {
    stage('Compile') {
      steps {
        git 'https://github.com/JP007-star/RestaurantApp.git'
        script{
                def mvnHome = tool name: 'MAVEN_HOME', type: 'maven'
                sh "${mvnHome}/bin/mvn package"
        }
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
