
 pipeline {
    agent any
    stages {
        stage('git repo & clean') {
            steps {
             
                bat "git clonehttps://github.com/JP007-star/RestaurantApp.git"
                bat "mvn clean -f RestaurantApp"
            }
        }
        stage('install') {
            steps {
                bat "mvn install -f RestaurantApp"
            }
        }
        stage('test') {
            steps {
                bat "mvn test -f RestaurantApp"
            }
        }
        stage('package') {
            steps {
                bat "mvn package -f RestaurantApp"
            }
        }
    }
}
