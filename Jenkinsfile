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
          sh'java -version'
          sh 'mvn clean install -Dmaven.test.skip=true'

          

      }
    }
   stage('deploy') {
            steps {
                sh'ls -l target/'
                 sh'mvn deploy -Dmaven.test.skip=true'
                
            }
        }
       
            stage('Create SBOM') {
            steps {
                sh'ls -l target/'
                    dependencyTrackPublisher artifact: 'target/CycloneDX-Sbom.xml', projectName: 'projet-spring-rest', projectVersion: '02', synchronous: true
                
            }
        }
    }
  }

