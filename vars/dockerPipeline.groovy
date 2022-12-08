def call(Map params){
	pipeline{
		agent any
		environment {
			dockerhub_repository = 'ripesparktechs/web'
			dockerhub_credentials = 'ripe-dockerhub-credentials'
		}
		
		
		
		stages{
			stage('Github stage') {
				steps {
					git(
						url: 'https://github.com/sparktechsllc/DISC.git',
						credentialsId: 'jonathan-github-token',
						branch: params.GitBranch
					)
				}
            }
			
			stage('Build Stage'){
				script{
					dockerDefs.composeBuild(
						ComposeFile: params.ComposeFile,
						Service: params.Service,
						Tag: params.Tag,
					)
				}
			}
			
			// stage('Unittest Stage'){
			// 	dockerDefs.unitTest(
			// 		UnitTestService: params.UnitTestService,
			// 	)
			// }
			
			stage('Deploy Stage'){
				script{
					dockerDefs.composeUp(
						ComposeFile: params.ComposeFile,
						Service: params.Service,
						Tag: params.Tag,
					)
				}
			}
		}
	}
}
