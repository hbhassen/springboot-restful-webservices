pipeline {
    
  agent any
  stages {
    stage('clone') {
      steps {
          echo 'hello'
          checkout scm

      }
    }
    stage('install') {
      steps {
          echo 'hi'
          sh'pwd'
          sh'ls -l'
          sh 'mvn clean install -Dmaven.test.skip=true'

          

      }
    }
            stage('Create SBOM') {
            steps {
                    dependencyTrackPublisher artifact: 'target/springboot-restful-webservices-0.0.1-SNAPSHOT.jar', projectName: 'StestSbom', projectVersion: '02', synchronous: true
                
            }
        }
    }
  }

