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
    stage('Mysql Container Creation') {
      steps {
        sh "docker run -d -p 3307:3306 --name mysqldb -e MYSQL_ROOT_PASSWORD=Prasad@66 -e MYSQL_DATABASE=restaurantapp mysql"
      }
     }
    stage('Docker network creation') {
      steps {
        sh "docker  network create restaurant"
      }
    }
   stage('Connect docker network and container') {
    steps {
        sh "docker network connect restaurant mysqldb"
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
  
  
  
  }
}
