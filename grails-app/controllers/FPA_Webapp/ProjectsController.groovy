package FPA_Webapp

import grails.converters.JSON

class ProjectsController {

    def index() {}

    def projectsService = new ProjectService();

    static allowedMethods = [ showcount: "GET" , doCreateProject:"POST" ]

    def list = {
        render projectsService.getAllProjects() as JSON
    }


    def doCreateProject ()
    {
        println("doProjectCreate");
    }

}
