node {
    stage ('SCM Pull') {
        checkout scm
    }
    
    stage ('Build and Package') {
        diplomovkaWithGradle = docker.build("diplomovka_gradle", "--file Dockerfile .") 
    }
    
    stage ('Test') {
        dockerGroupId = sh 'cut -d: -f3 < <(getent group docker)'
        diplomovkaWithGradle.inside ('-v $PWD:$PWD -w $PWD -v /var/run/docker.sock:/var/run/docker.sock --group-add ${dockerGroupId}') {     
            sh 'gradle test'
        }
    }

    stage ('Build Docker Image') {
        diplomovkaImage = docker.build("1953/diplomovka", "--file Dockerfile_ci .")
    }

    stage ('Publish Image') {
        docker.withRegistry('https://registry.hub.docker.com', 'docker') {            
            diplomovkaImage.push("${env.BUILD_NUMBER}")            
            diplomovkaImage.push("latest")        
        }
    }    
}