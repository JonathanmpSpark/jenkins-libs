def build(Map params){
	ssh "echo 'execute from build funtion {params.name}'"
//	ssh "docker build -f ${params.Dockerfile} -t ${params.DockerImage} ${params.DockerContext}"
}

def push(Map params){
	ssh "docker push ${params.DockerImage}"
}
