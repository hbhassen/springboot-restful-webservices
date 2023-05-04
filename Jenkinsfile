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
       stage('Check SBOM') {
            steps {
                sh 'dependency-check.sh --project "StestSbom" --scan /var/lib/jenkins/workspace/test_main/ --format "XML" --out target/bom.xml'
                   
            }
        }
            stage('Create SBOM') {
            steps {
                sh'ls -l target/'
                    dependencyTrackPublisher artifact: 'target/bom.xml', projectName: 'StestSbom', projectVersion: '02', synchronous: true
                
            }
        }
    }
  }

