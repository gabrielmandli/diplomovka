node {
    checkout scm

    def diplomovkaImage = docker.build("1953/diplomovka", "--file Dockerfile_ci .") 

    docker.withRegistry('https://registry.hub.docker.com', 'docker') {            
       diplomovkaImage.push("${env.BUILD_NUMBER}")            
       diplomovkaImage.push("latest")        
    }    
}