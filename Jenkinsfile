import groovy.sql.Sql
pipeline {
  //  agent any
    
node{

    def sql = Sql.newInstance("jdbc:mysql://mysql:3306/resturant", "root","anil123", "com.mysql.jdbc.Driver")
   // def rows = sql.execute "select count(*) from ;"
   // echo rows.dump()
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
}