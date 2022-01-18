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
   
   stage('Mysql deployment') {
     steps{
       script{
           kubernetesDeploy(configs:"db-deployment.yml",kubeconfigId:"newjp")
       }
       
     }
   }
   stage('App deployemnt') {
     steps{
       script{
           kubernetesDeploy(configs:"app-deployment.yml",kubeconfigId:"newjp")
       }
       
     }
   }
        
 
     
  
 
  }
}
