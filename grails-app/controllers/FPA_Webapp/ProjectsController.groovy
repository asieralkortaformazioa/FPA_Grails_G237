package FPA_Webapp

import grails.converters.JSON

class ProjectsController {

    def index() {}

    def projectsService = new ProjectService();


    def list = {
        render projectsService.getAllProjects() as JSON
    }
}
