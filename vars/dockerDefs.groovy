def build(Map params){
	sh "date"
	sh 'date'
	sh 'Execute from dockerDefs. ${params.Name}'
//	sh "docker build -f ${params.Dockerfile} -t ${params.DockerImage} ${params.DockerContext}"
}

def push(Map params){
	sh "docker push ${params.DockerImage}"
}

def test(Map params){
	sh 'echo "HELLO WORLD!!! ${params.Name}"'
}
