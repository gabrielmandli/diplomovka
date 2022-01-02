node {
    stage ('SCM Pull') {
        checkout scm
    }
    
    stage ('Build and Package') {
        diplomovkaWithGradle = docker.build("diplomovka_gradle", "--file Dockerfile .") 
    }
    
    stage ('Test') {
        // def dockerGroupId = sh "awk -F\\: '{print \"Group \" \$1 \" with GID=\" \$3}' /etc/group | grep \"docker\" | grep -o '=.*' | cut -c 2-"
        // echo dockerGroupId
        // def dockerGroupId = sh 'cat /etc/group | grep "docker" | grep -o "x:.*:" | cut -c 3- | rev | cut -c2- | rev'
        // sh "echo ${dockerGroupId}"

        sh 'cat /etc/group | grep "docker" | grep -o "x:.*:" | cut -c 3- | rev | cut -c2- | rev > commandResult'
        dockerGroupId = readFile('commandResult').trim()
        sh "echo ${dockerGroupId}"

        // diplomovkaWithGradle.inside ("-v $PWD:$PWD -w $PWD -v /var/run/docker.sock:/var/run/docker.sock --group-add ${dockerGroupId}") {     
        //     sh 'gradle test'
        }
    }

    // stage ('Build Docker Image') {
    //     diplomovkaImage = docker.build("1953/diplomovka", "--file Dockerfile_ci .")
    // }

    // stage ('Publish Image') {
    //     docker.withRegistry('https://registry.hub.docker.com', 'docker') {            
    //         diplomovkaImage.push("${env.BUILD_NUMBER}")            
    //         diplomovkaImage.push("latest")        
    //     }
    // }    
// }