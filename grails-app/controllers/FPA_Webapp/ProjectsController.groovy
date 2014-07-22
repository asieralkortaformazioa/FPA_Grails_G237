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


    def doEdit (){
        println ("EditProject")
        String id= null;
        String name=null;
        def json = request.JSON
        if (json !=null) {
            id = json.getAt("projects");

            name = json.getAt("projectName");
        }

//        if (params.projects!=null && !"".equals(params.projects))
//            id= params?.projects?.toInteger()
//

//        if (params.projectName!=null && !"".equals(params.projectName))
//            name= params?.projectName

        def ok = projectsService.saveProject (id?.toInteger(), name);

//        render "{ \"result\":\""+ok+"\" } ";
        render "{ \"result\":"+ok+" } "
    }

}
