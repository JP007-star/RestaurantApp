import groovy.sql.Sql
pipeline {
   agent any
    
tools
   {
      maven 'M3'
      jdk 'jdk'
   } 
   
   

   
    stages { 
        stage('Build') {
            steps {
                // Get some code from a GitHub repository
                git 'https://github.com/JP007-star/RestaurantApp.git'

                // Run Maven on a Unix agent.
                sh "mvn -Dmaven.test.failure.ignore=true clean package"

                // To run Maven on a Windows agent, use
                // bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }

            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
           
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

                            nexusUrl: '192.168.1.12:8081',

                            groupId: 'pom.com.restaurant.app',

                            version: 'pom.0.0.1-SNAPSHOT',

                            repository: 'maven-restaurant',

                            credentialsId: 'nexus_cred',

                            artifacts: [

                                [artifactId: 'pom.restaurantApp',

                                classifier: '',

                                file: artifactPath,

                                type: pom.packaging],

                                [artifactId: 'pom.restaurantApp',

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
    }


