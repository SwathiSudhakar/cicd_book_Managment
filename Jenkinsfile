pipeline {
    agent any
    tools {
        maven 'maven'
    }

    stages {
        stage('Git Checkout') {
            steps {
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/SwathiSudhakar/cicd_book_Managment.git']])
                echo 'Git Checkout Completed'
            }
        }
    }
}
