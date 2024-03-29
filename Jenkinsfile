node {
    stage ('SCM Pull') {
        checkout scm
    }
    
    stage ('Build and Package') {
        diplomovkaWithGradle = docker.build("diplomovka_gradle") 
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
        diplomovkaWithJdk = docker.build("1953/diplomovka", "--file Dockerfile_ci_jdk .")
    }

    stage ('Publish Image') {
        docker.withRegistry('https://registry.hub.docker.com', 'docker') {            
            diplomovkaWithJdk.push("${env.BUILD_NUMBER}")            
            diplomovkaWithJdk.push("latest")        
        }
    }

    stage ('Deploy to Integration') {
        sshagent(credentials : ['Integration_key']) {
            sh 'ssh -o StrictHostKeyChecking=no ubuntu@ec2-18-193-135-203.eu-central-1.compute.amazonaws.com uptime'
            sh 'ssh -v ubuntu@ec2-18-193-135-203.eu-central-1.compute.amazonaws.com'
            sh 'scp ./docker-compose-deploy.yml ubuntu@ec2-18-193-135-203.eu-central-1.compute.amazonaws.com:/diplomovka/docker-compose.yml'

            sh 'ssh -v ubuntu@ec2-18-193-135-203.eu-central-1.compute.amazonaws.com docker-compose -f /diplomovka/docker-compose.yml pull'
            sh 'ssh -v ubuntu@ec2-18-193-135-203.eu-central-1.compute.amazonaws.com docker-compose -f /diplomovka/docker-compose.yml up -d'
        }
    }
}