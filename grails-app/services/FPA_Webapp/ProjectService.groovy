package FPA_Webapp

import FPA_Webapp_G237.Functionality
import FPA_Webapp_G237.Projects
import core.FunctionTypes
import org.springframework.transaction.annotation.Transactional

/**
 * Created by developer on 28/05/14.
 */
@Transactional
class ProjectService {


    def getAllProjects ()
    {

        def projects = Projects.findAll()
        println ("Projects:"+projects);
        return projects;

    }

    def getProject (Integer id)
    {
        def proj = Projects.get(id);
        return proj;
    }

    def getProjectFunctionalities (Integer idProject)
    {

        println("idProj:"+idProject);

        def proj = getProject (idProject);

//        def functionalities = Functionality.where {
//            //eq ("idProject", idProject)
//            eq ("projects", proj)
//        }

        def functs = Functionality.findAll();


        def result = new ArrayList <Functionality>();
        for (funct in functs) {
            funct.projects.each {
                if (proj.id == it.id) {
                    result.add(funct)
                }
            }
        }
        return result;
    }


    def addProject (String desc)
    {
        def proj = new Projects(description: desc);
        proj.save();
    }

    def removeProject (Integer id)
    {
        def p = Projects.get(id)
        p.delete();
    }

    def createFunctionality (String description, FunctionTypes ftype , Integer hcount, Integer vcount, Integer idProy)
    {
        println ("Description:"+description);
        Projects proj = Projects.get(idProy);

         def f = new Functionality (type:ftype?.name(), description:description, hcount: hcount, vcount: vcount , projects:proj, idProject: idProy);
        def created = f.save(flush: true);

         return created;

    }


    def removeFunctionality (Integer id)
    {
        def f = Functionality.get(id)
        f.delete(flush: true)

    }

}
