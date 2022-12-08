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
				steps {
					script{
						dockerDefs.composeBuild(
							ComposeFile: params.ComposeFile,
							Service: params.Service,
							Tag: params.Tag,
						)
					}
				}
			}
			
			stage('Unittest Stage'){
				steps {
					script {
						
						if(params.UnitTestService){
							echo "Si se mando el parametro y es: ${params.UnitTestService}!"
							echo "Si se mando el parametro y es: ${params.UnitTestService}!"
							echo "Si se mando el parametro y es: ${params.UnitTestService}!"
							echo "Si se mando el parametro y es: ${params.UnitTestService}!"
						}
						else{
							echo "el parametro UnitTestService esta vacio!"
							echo "el parametro UnitTestService esta vacio!"
							echo "el parametro UnitTestService esta vacio!"
							echo "el parametro UnitTestService esta vacio!"
							echo "el parametro UnitTestService esta vacio!"
						}
					
						// dockerDefs.unitTest(
						// 	UnitTestService: params.UnitTestService,
						// )
					}
				}
			}
			
			stage('Deploy Stage'){
				steps {
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
}
