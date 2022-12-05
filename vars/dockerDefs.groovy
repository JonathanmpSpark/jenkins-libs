def build(Map params){
	sh 'echo "Execute from dockerDefs."'
//	sh "docker build -f ${params.Dockerfile} -t ${params.DockerImage} ${params.DockerContext}"
}

def push(Map params){
	sh "docker push ${params.DockerImage}"
}

def test(Map params){
	echo "My name is  ${params.Name}"
}
