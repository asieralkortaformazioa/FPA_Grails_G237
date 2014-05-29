package FPA_Webapp

import grails.converters.JSON


class FunctionalitiesController {

    def index() {}


    def projectsService = new ProjectService();



    def list ()
    {
        request.parameterMap.get()
        return ""
    }

    def listProjectFunctionalities ()
    {
        println ("ParamMap:"+request.parameterMap)
        println ("params:"+params)
        println ("listProjectFunctionalities");
//        Integer idProj = request.parameterMap.get("idProject")?.toString();
        String strIdproj = params.get("idProject")
        Integer idProj = params.get("idProject").toString().toInteger();
        def functs = projectsService.getProjectFunctionalities(idProj);
        render functs as JSON;

    }
}
