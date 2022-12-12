def call(Map params){
	pipeline{
		environment {
            docker_image = ''
        }
        
        agent any
        
        stages {
            stage('Github stage') {
                steps {
                    git(
                        url: 'https://github.com/sparktechsllc/DISC.git',
                        credentialsId: 'jonathan-github-token',
                        branch: params.GitBranch
                    )
                }
            }
            
            stage('Build Stage') {
                steps {
                    script {
                        echo "[+] ---> Building ${params.Repo}:${params.Tag}"
                        docker_image = docker.build(
                            "${params.Repo}:${params.Tag}",
                            "-f ./compose/preprod/services/api-base/Dockerfile ."
                        )
                    }
                }
            }
            
            stage('Push Stage') {
                steps {
                    script{
                        docker.withRegistry("https://${params.Repo}", params.RegistryCredentials) {
                            echo "[+] ---> Pushing to https://${params.Repo}"
                            docker_image.push('latest')
                            docker_image.push()
                        }
                    }
                }
            }
        }
		
		
		post{
			success {
				script {
					dockerDefs.pruneImages()
				}
			}
		}
	}
}
