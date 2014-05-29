package FPA_Webapp

import adapters.FunctionalityTableAdapter
import core.FunctionTypes
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
        def adapter = new FunctionalityTableAdapter();

        def res =adapter.adapt(functs)

        render res as JSON;

    }

    def create ()
    {
        println("params"+params)
        def desc = params.description
        def type = FunctionTypes.valueOf(params?.type)
        Integer hcount = params.hCount.toInteger ();
        Integer vcount = params.vCount.toInteger ();

        def res = projectsService.createFunctionality(desc, type, hcount, vcount, params.idProj?.toInteger())
        render res as JSON;
    }

    def delete ()
    {
        println ("Deleting...")
        Integer id = params?.id?.toInteger ();
        projectsService.removeFunctionality(id)
        render "" as JSON;
    }
}
