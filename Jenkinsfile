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
                sh "mvn -Dmaven.test.failure.ignore=true clean package"
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


