def composeBuild(Map params){
	sh "IMAGE_TAG='${params.Tag}' docker-compose -f ${params.ComposeFile} build ${params.Service}"
}

def composeUp(Map params){
	sh "IMAGE_TAG='${params.Tag}' docker-compose -f ${params.ComposeFile} up -d ${params.Service}"
}

def unittest(Map params){
	sh "docker exec -i -u root ${params.ContainerName} bash -c pytest --html=/app/templates/pytest"
}


def teamsNotification(Map params){
	sh " docker run --rm deployment-notificator notificator.py ${params.WebHookKey} ${params.Message}"
}
