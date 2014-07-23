package FPA_Webapp

import grails.converters.JSON

class AdjustmentFactorController {

    def projectsService = new ProjectService();

    def index() {
        redirect (action : "saveAdjustmentFactor", params: params)
    }



    def saveAdjustmentFactor (){
        println("saveAdjustmentFactor")

        def id = null;

        def json = request.JSON
        if (json !=null) {
            id = json.getAt("projects");

            name = json.getAt("projectName");
        }

        String strIdproj = params.get("projects")
        Integer idProject = strIdproj.toInteger();


        for (int i=1;  i<=14;i++)
        {
            String strAf1Response = params.get("af"+i+"Count")
            projectsService.saveAdjustmentFactor (idProject, i, strAf1Response);
        }

//        return "{result:true}"

    }


    def getAdjustmentFactors ()
    {
        String strIdproj = params.get("idProject")
        Integer idProject = strIdproj.toInteger();
        def afs = projectsService.getAdjustmentFactors (idProject);
        render afs as JSON;
    }


}
