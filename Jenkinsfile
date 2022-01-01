node {
    stage ('SCM Pull') {
        checkout scm
    }
    
    stage ('Build and Package') {
        docker.build("diplomovka_gradle", "--file Dockerfile .") 
    }
    
    stage ('Test') {
        docker.image('diplomovka_gradle')
            .withRun('-v //var/run/docker.sock:/var/run/docker.sock')
            .inside {
            sh 'cd diplomovka'
            sh 'gradle test'
        }
    }

    stage ('Build Docker Image') {
        def diplomovkaImage = docker.build("1953/diplomovka", "--file Dockerfile_ci .")
    }

    stage ('Publish Image') {
        docker.withRegistry('https://registry.hub.docker.com', 'docker') {            
            diplomovkaImage.push("${env.BUILD_NUMBER}")            
            diplomovkaImage.push("latest")        
        }
    }    
}