def diplomovkaWithGradle

node {
    stage ('SCM Pull') {
        checkout scm
    }
    
    stage ('Build and Package') {
        diplomovkaWithGradle = docker.build("diplomovka_gradle", "--file Dockerfile .") 
    }
    
    stage ('Test') {
        diplomovkaWithGradle.inside ('-v //var/run/docker.sock:/var/run/docker.sock') {
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