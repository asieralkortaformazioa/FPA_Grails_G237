package fpa_webapp_g237

import FPA_Webapp.ProjectService

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces

@Path('/api/functionalities')
class FunctionalitiesResource {


    def projectsService = new ProjectService();

    @GET
    @Produces('text/json')
    String getFunctionalitiesRepresentation() {
//        param.
    }
}
