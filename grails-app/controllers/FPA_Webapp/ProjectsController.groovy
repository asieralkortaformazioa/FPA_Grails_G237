package FPA_Webapp

import FPA_Webapp_G237.Projects
import grails.converters.JSON

class ProjectsController {

    def index() {}

    def projectsService = new ProjectService();
    def importService = new ImportService();

    //static allowedMethods = [ showcount: "GET" , doCreateProject:"POST" ]

    def list = {
        render projectsService.getAllProjects() as JSON
    }


    def getProjectConfiguration (){
        println "getProjectConfiguration"
        Integer id = null;

        if (params.id!=null && !"".equals(params.id))
            id=params.id.toInteger();

        Projects proj = projectsService.getProject(id);
        String result = "{\"productivity\":\""+proj?.getProductivity()+"\"}"
        render result
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


    def doImportProject ()
    {
        String file = params?.get("fileName")
//        if (file ==null) {
            file = "/mnt/shared/Caf/Projects/Calidad/Dokumentazioa/FPA_FP_Count_Invertido-v01.xls";
//        }

        boolean ok = importService.importFromExcel(file);
        render "{ \"result\":"+ok+" } "
    }

}

