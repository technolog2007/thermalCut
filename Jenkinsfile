pipeline {
    agent any
    tools{
    maven "3.9.0"
    }
    stages {
        stage('Build') { 
            steps {
                sh 'mvn -version'
                sh 'mvn clean package'
            }
        }
    }
}