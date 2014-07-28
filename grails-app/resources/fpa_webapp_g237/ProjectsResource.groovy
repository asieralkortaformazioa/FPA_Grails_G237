package fpa_webapp_g237

import FPA_Webapp.ProjectService
import grails.converters.JSON

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces

@Path('/api/projects')
class ProjectsResource {

    ProjectService projectService = new ProjectService();

    @GET
    @Produces('text/json')
    String getProjectsRepresentation() {
//        'FPA_Webapp_G237.FPA_Webapp_G237.FPA_Webapp_G237.FPA_Webapp_G237.Projects'

        println ("Indexing..");
        def list = projectService.getAllProjects();

        def converter = new JSON(target: list);
        def str = converter.toString();

        println ("Str:"+str)

        //respond model:[projects: list]
        return str;

    }
}
