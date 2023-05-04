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
                sh'ls -l target/'
                    dependencyTrackPublisher artifact: 'bom.xml', projectName: 'NomDuProjet', projectVersion: '02', synchronous: true
                
            }
        }
    }
  }

