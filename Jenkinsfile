pipeline {
    agent any
    tools {
        maven 'maven'
    }

    stages {
        stage('Git Checkout') {
            steps {
                checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/SwathiSudhakar/User_App_Service.git']])
                echo 'Git Checkout Completed'
            }
        }
    }
}
