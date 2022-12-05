def compose-build(Map params){
	sh "IMAGE_TAG='${params.Tag}' docker-compose -f ${params.ComposeFile} build ${params.services}"
}

def compose-up(Map params){
	sh "IMAGE_TAG='${params.Tag}' docker-compose -f ${params.ComposeFile} up -d ${params.services}"
}

def unittest(Map params){
	sh "docker exec -i -u root ${params.service} bash -c pytest --html=/app/templates/pytest"
}
