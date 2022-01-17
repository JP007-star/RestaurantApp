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
   
   stage('SSH into the server') {
        script {
            def remote = [:]
            remote.name = 'server'
            remote.host = '192.168.1.4'
            remote.user = 'server'
            remote.password = 'server'
            remote.allowAnyHosts = true
            sshPut remote: remote, from: 'db-deployment.yml', into: '.'
            sshCommand remote: remote, command: "kubectl apply -f db-deployment.yml"
          }
   }
        
 
     
  
 
  }
}
