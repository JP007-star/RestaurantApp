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
   stage("SSH Into k8s Server") {
        def remote = [:]
        remote.name = 'server'
        remote.host = '192.168.1.4'
        remote.user = 'server'
        remote.password = 'server'
        remote.allowAnyHosts = true
   } 
    
  }
}
