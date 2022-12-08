def composeBuild(Map params){
	sh "IMAGE_TAG='${params.Tag}' docker-compose -f ${params.ComposeFile} build ${params.Service}"
}

def composeUp(Map params){
	sh "IMAGE_TAG='${params.Tag}' docker-compose -f ${params.ComposeFile} up -d ${params.Service}"
}

def pruneImages(){
	sh "docker image prune -f"
}

def unitTest(Map params){
	sh "mkdir $HOME/pytest &> /dev/null"
	sh "docker run --rm --name unittest -u root -v $HOME/pytest:/app/templates/pytest -e DJANGO_SETTINGS_MODULE=config.settings.staging --entrypoint pytest ${params.UnitTestImage} '--html=/app/templates/pytest/report.html'"
	//sh "docker cp $HOME/pytest/report.html api:/app/templates/pytest/"
}

def teamsNotification(Map params){
	sh " docker run --rm deployment-notificator notificator.py ${params.WebHookKey} '${params.Message}'"
}
