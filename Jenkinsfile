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
   stage('generate SBOM') {
            steps {
                sh'ls -l target/'
                 sh 'mvn org.cyclonedx:cyclonedx-maven-plugin:makeBom
                 sh'cat target/CycloneDX-Sbom.xml'
                
            }
        }
       
    }
  }

