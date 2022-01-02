node {
    stage ('SCM Pull') {
        checkout scm
    }
    
    stage ('Build and Package') {
        diplomovkaWithGradle = docker.build("diplomovka_gradle", "--file Dockerfile .") 
    }
    
    stage ('Test') {
        // Fetch the GID of the group "docker"
        sh 'cat /etc/group | grep "docker" | grep -o "x:.*:" | cut -c 3- | rev | cut -c2- | rev > commandResult'
        dockerGroupId = readFile('commandResult').trim()

        diplomovkaWithGradle.inside ("-v /var/run/docker.sock:/var/run/docker.sock --group-add ${dockerGroupId}") {     
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