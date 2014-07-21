package FPA_Webapp

import adapters.FunctionalityTableAdapter
import core.FunctionTypes
import grails.converters.JSON


class FunctionalitiesController {

    def index() {}


    def projectsService = new ProjectService();

//    static allowedMethods = [ listProjectFunctionalities: "GET" , edit:"PUT", create :"POST", delete:"DELETE" ]


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

        Integer idProj =null;
        if (strIdproj!=null) {
//            String strIdproj = params.get("idProject");
            idProj = strIdproj?.toString()?.toInteger();
        }
        println("idProject:"+idProj);
        def functs = projectsService.getProjectFunctionalities(idProj);
        def adapter = new FunctionalityTableAdapter();

        println("functs:"+functs)
        def res =adapter.adapt(functs)
        println (res);
        def json = res as JSON;

        println ("json"+json);
        render json;

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
        println ("Id: "+id);
        projectsService.removeFunctionality(id)
//        Boolean res = new Boolean (true);
        //render null as JSON;
        return "{\"result\":\"true\"}"
    }

    def edit ()
    {
        println("params"+params)
        def desc = params?.description;

        def strType = params?.type;

        def type = strType!=null?FunctionTypes.valueOf(strType):null;
        Integer hcount = params?.hCount?.toInteger ();
        Integer vcount = params?.vCount?.toInteger ();

        def res = projectsService.createFunctionality(desc, type, hcount, vcount, params.idProj?.toInteger())
        def json = res as JSON;
        println ("json:"+json);
        render json;
    }

}
