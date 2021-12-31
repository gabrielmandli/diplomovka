node {
    checkout scm

    def diplomovkaImage = docker.build("diplomovka", "./Dockerfile_ci") 

    diplomovkaImage.push('latest')
}