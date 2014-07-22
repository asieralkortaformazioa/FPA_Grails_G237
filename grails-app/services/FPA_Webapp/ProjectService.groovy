package FPA_Webapp

import FPA_Webapp_G237.AdjustmentFactor
import FPA_Webapp_G237.Functionality
import FPA_Webapp_G237.Projects

//
//import FPA_Webapp_G237.FPA_Webapp_G237.FPA_Webapp_G237.FPA_Webapp_G237.Functionality
//import FPA_Webapp_G237.FPA_Webapp_G237.FPA_Webapp_G237.FPA_Webapp_G237.Projects
//import FPA_Webapp_G237.FPA_Webapp_G237.FPA_Webapp_G237.FPA_Webapp_G237.FPA_Webapp_G237.AdjustmentFactor.FPA_Webapp_G237.FPA_Webapp_G237.FPA_Webapp_G237.AdjustmentFactor
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
        println ("FPA_Webapp_G237.FPA_Webapp_G237.FPA_Webapp_G237.Projects:"+projects);
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

//        def functionalities = FPA_Webapp_G237.FPA_Webapp_G237.FPA_Webapp_G237.Functionality.where {
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
        proj.save(flush:true);
    }

    def removeProject (Integer id) {
        Boolean removed = false;
        println "Deleting project id:" + id
        if (id != null) {
            try {
                def p = Projects.get(id)
                println "Deleting project:" + p
                p?.delete(flush: true);
                removed = true
            } catch (Exception e) {
                removed = false
                e.printStackTrace();
            }
        }
        return removed ;
    }


    def saveProject (Integer idProject, String desc)
    {
        Boolean ok = false;
        if (idProject!=null) {
            Projects proj = Projects.get(idProject)
            proj.setDescription(desc)
            proj.save(flush:true);
            ok=true;
        }
        return ok;
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
        println ("Removing functionality "+id);
        def f = Functionality.get(id)
        f.delete(flush: true)

    }


    def saveAdjustmentFactor (int idProject, int idQuestion, String response)
    {
        Projects proj = Projects.get (idProject);
        def af = new AdjustmentFactor (projects:proj, idProject:idProject, idQuestion:idQuestion, response:response);
        af.setIdProject(idProject)
        af.setIdQuestion(idQuestion)
        af.setResponse(response!=null? response.toInteger():null)
        af.setProjects(proj)

        println "Saving:"+af;
        println (af.getIdProject())
        println (af.getIdQuestion())
        println (af.getResponse())
        println (af.getProjects())
        def created = af.save(flush:true);
        return created;

    }


    def getAdjustmentFactors (Integer idProject)
    {
//        def afs = FPA_Webapp_G237.FPA_Webapp_G237.FPA_Webapp_G237.AdjustmentFactor.where{
//            eq("projects_id", idProject)
//        }
        def idProj=idProject
        def allAfs= AdjustmentFactor.getAll();

        def afs = new ArrayList <AdjustmentFactor>();
        for (af in allAfs) {
            af.projects.each {
                if (idProj == it.id) {
                    afs.add(af);
                }
            }
        }
        println (afs);
        return afs;


//        def functionalities = FPA_Webapp_G237.FPA_Webapp_G237.FPA_Webapp_G237.Functionality.where {
//            //eq ("idProject", idProject)
//            eq ("projects", proj)
//        }
    }

}
