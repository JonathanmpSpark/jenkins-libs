def build(Map params){
	ssh "date"
	ssh 'date'
	ssh 'Execute from dockerDefs.'
//	ssh "docker build -f ${params.Dockerfile} -t ${params.DockerImage} ${params.DockerContext}"
}

def push(Map params){
	ssh "docker push ${params.DockerImage}"
}
