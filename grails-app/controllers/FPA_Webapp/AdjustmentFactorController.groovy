package FPA_Webapp

import grails.converters.JSON

class AdjustmentFactorController {

    def projectsService = new ProjectService();

    def index() {}

    def save ()
    {
        String strIdproj = params.get("idProject")
        Integer idProject = strIdproj.toInteger();


        for (int i=1;  i<=14;i++)
        {
            String strAf1Response = params.get("af"+i+"Count")
            projectsService.saveAdjustmentFactor (idProject, i, strAf1Response);
        }

    }


def getAdjustmentFactors ()
{
    String strIdproj = params.get("idProject")
    Integer idProject = strIdproj.toInteger();
    def afs = projectsService.getAdjustmentFactors (idProject);
    render afs as JSON;
}


}
