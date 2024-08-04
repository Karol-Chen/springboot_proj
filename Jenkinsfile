pipeline {
    agent any

    stages {
        stage('pull code') {
            steps {
checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[credentialsId: '01b626e4-b6e9-46e5-a97a-7072d36c65b0', url: 'https://github.com/Karol-Chen/springboot_proj']])
            }
        }
        stage('build project'){
            steps{
                sh 'mvn clean package'
            }
        }
    }
}
