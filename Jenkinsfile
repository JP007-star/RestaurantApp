pipeline {
  
  environment {
    registry = "justiceofpeace/restaurant_app"
    MAVEN_HOME = tool('MAVEN3')
    DOCKER_HOME= tool('docker')
    registryCredential = 'docker-hub-credentials'
    dockerImage = ''
  }
  agent any
  tools{
    jdk "JAVA8"
  }
  stages {
    stage('Compile & Build ') {
      steps {
        sh "${MAVEN_HOME}/bin/mvn package"
      }
    }
     stage("Publish to Nexus Repository Manager") {

            steps {

                script {

                    pom = readMavenPom file: "pom.xml";

                    filesByGlob = findFiles(glob: "target/*.${pom.packaging}");

                    echo "${filesByGlob[0].name} ${filesByGlob[0].path} ${filesByGlob[0].directory} ${filesByGlob[0].length} ${filesByGlob[0].lastModified}"

                    artifactPath = filesByGlob[0].path;

                    artifactExists = fileExists artifactPath;

                    if(artifactExists) {

                        echo "*** File: ${artifactPath}, group: ${pom.groupId}, packaging: ${pom.packaging}, version ${pom.version}";

                        nexusArtifactUploader(
                            nexusVersion: 'nexus3',
                            
                            protocol: 'http',

                            nexusUrl: '3.88.176.235:8081',

                            groupId: 'pom.com.restaurant.app',

                            version: 'pom.0.0.1',

                            repository: 'resturant-repo',

                            credentialsId: 'nexus_cred',

                            artifacts: [

                                [artifactId: 'pom.RestaurantApp',

                                classifier: '',

                                file: artifactPath,

                                type: pom.packaging],

                                [artifactId: 'pom.RestaurantApp',

                                classifier: '',

                                file: "pom.xml",

                                type: "pom"]

                            ]

                        );

                    } else {

                        error "*** File: ${artifactPath}, could not be found";

                    }

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
              docker.withRegistry('https://registry.hub.docker.com', 'docker-hub-credentials')
          {
              dockerImage.push("${env.BUILD_NUMBER}")
              dockerImage.push("latest")
          }
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
