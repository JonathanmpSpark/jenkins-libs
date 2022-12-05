def compose-build(Map params){
	sh "IMAGE_TAG='${params.Tag}' docker-compose -f ${params.ComposeFile} build ${params.Service}"
}

def compose-up(Map params){
	sh "IMAGE_TAG='${params.Tag}' docker-compose -f ${params.ComposeFile} up -d ${params.Service}"
}

def unittest(Map params){
	sh "docker exec -i -u root ${params.Service} bash -c pytest --html=/app/templates/pytest"
}
