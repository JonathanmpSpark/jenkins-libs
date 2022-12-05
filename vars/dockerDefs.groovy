def build(Map params){
	ssh "date"
	ssh 'date'
	ssh 'Execute from dockerDefs. ${params.Name}'
//	ssh "docker build -f ${params.Dockerfile} -t ${params.DockerImage} ${params.DockerContext}"
}

def push(Map params){
	ssh "docker push ${params.DockerImage}"
}

def test(Map params){
	ssh 'echo "HELLO WORLD!!!"'
}
