node {
    checkout scm

    def diplomovkaImage = docker.build("diplomovka", "--file Dockerfile_ci .") 

    diplomovkaImage.push('latest')
}