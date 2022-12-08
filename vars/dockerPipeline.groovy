def call(Map params){
	pipeline{
		agent any
		environment {
			dockerhub_repository = 'ripesparktechs/web'
			dockerhub_credentials = 'ripe-dockerhub-credentials'
		}
		
		stage('Github stage') {
            steps {
                git(
                    url: 'https://github.com/sparktechsllc/DISC.git',
                    credentialsId: 'jonathan-github-token',
                    branch: params.GitBranch
                )
            }
        }
		
		stages{
			stage('Build Stage'){
				dockerDefs.composeBuild(
					ComposeFile: params.ComposeFile,
					Service: params.Service,
					Tag: params.Tag,
				)
			}
			
			// stage('Unittest Stage'){
			// 	dockerDefs.unitTest(
			// 		UnitTestService: params.UnitTestService,
			// 	)
			// }
			
			stage('Deploy Stage'){
				dockerDefs.composeUp(
					ComposeFile: params.ComposeFile,
					Service: params.Service,
					Tag: params.Tag,
				)
			}
		}
	}
}
