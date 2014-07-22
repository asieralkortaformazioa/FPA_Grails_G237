package FPA_Webapp

import grails.converters.JSON

class ProjectsController {

    def index() {}

    def projectsService = new ProjectService();

    //static allowedMethods = [ showcount: "GET" , doCreateProject:"POST" ]

    def list = {
        render projectsService.getAllProjects() as JSON
    }


    def doCreateProject ()
    {
        println("doProjectCreate");

        String projectName= null;
        if (params.projectName!=null && !"".equals(params.projectName))
            projectName=params.projectName;

        println ("Creating:"+projectName);
        projectsService.addProject(projectName);
        render "{\"result\":\"true\"}"
    }

    def deleteProject (){

        println "doDelete Project"
        Integer id =null;
        if (params.projects!=null && !"".equals(params.projects))
            id=params.projects.toInteger();
        println "ProjectId:"+id;
        def removed = projectsService.removeProject(id);
        render "{ \"result\":"+removed+" } "
    }

}
