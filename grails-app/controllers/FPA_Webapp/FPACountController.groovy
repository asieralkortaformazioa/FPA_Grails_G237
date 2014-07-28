package FPA_Webapp

import core.Calculations
import core.FpaViewBean
import grails.converters.JSON
import groovy.json.JsonBuilder

class FPACountController {

    def index() {
        redirect (action : "showCount", params: params)
//        redirect (action : "doCount", params: params)
    }
    //static allowedMethods = [ showcount: "GET" , doCount:"POST" ]

    def showCount = {
        println ("showCount");
        def viewBean = new FpaViewBean();
        getProjects (viewBean);
        render(view: "index", model: [viewBean: viewBean])
    }

    def countService = new CountService();
    def projectsService = new ProjectService();

    def doCalculate ()
    {
         println ("doCalculate");
        String idProjectStr = params.get("id")
        Integer idProject = idProjectStr.toInteger();
        Calculations calcs = countService.calculateProjectFps(idProject);

        //String result ="{ \"afps\":"+calcs.afps+", \"ufps\":"+calcs.ufps+", \"adjustmentFactor\":"+calcs.adjustmentFactor+" , \"dataFunctions\":"+calcs.dataFunctions+" , \"transactionalFunctions\":"+calcs.transactionalFunctions+" }";

        println "Calculations: "+calcs
        String result =new JsonBuilder( calcs ).toPrettyString()
        println result
        render result

    }

    def doCount ()
//    (@RequestParameter ('ilfRetCount ')  String  ilfRetCount, @RequestParameter ('ilfDetCount ')  String  ilfDetCount,
//                 @RequestParameter ('eifRetCount ')  String  eifRetCount, @RequestParameter ('eifDetCount ')  String  eifDetCount)
    {
        println("doCount");


        //ILF count
//        def ilfRetCount = params.ilfRetCount; //params.get('ilfRetCount')
//        def ilfDetCount = params.ilfDetCount ; //get('ilfDetCount')

        println("params:" + params.get('ilfRetCount'));
        println("params:" + params.ilfRetCount);
        println("type:" + CountService.ComplexityTypes.ILF.name())
//        println ("ilfRetCount:"+ilfRetCount)
//        println ("ilfDetCount:"+ilfDetCount)
        println("params:" + params)
        println("countService:" + countService)


        FpaViewBean viewBean = new FpaViewBean();



        if (params.ilfRetCount != null && !"".equals(params.ilfRetCount))
            viewBean.setIlfRetCount(params?.ilfRetCount?.toInteger());



        if (params.ilfDetCount != null && !"".equals(params.ilfDetCount))
            viewBean.setIlfDetCount (params?.ilfDetCount?.toInteger());



        if (params.eifRetCount != null && !"".equals(params.eifRetCount))
            viewBean.setEifRetCount(params?.eifRetCount?.toInteger());



        if (params.eifDetCount != null && !"".equals(params.eifDetCount))
            viewBean.setEifDetCount(params?.eifDetCount?.toInteger());



        String type = CountService.ComplexityTypes.ILF.name()

        println("type:" + type)


        println("Counting data functions: ")
        def ilfFPCountI = countService.getComplexityFromMatrixCount(CountService.ComplexityTypes.ILF.name(), viewBean.getIlfRetCount(), viewBean.getIlfDetCount(), viewBean)

        def eifFPCountI = countService.getComplexityFromMatrixCount(CountService.ComplexityTypes.EIF.name (), viewBean.getIlfRetCount(), viewBean.getIlfDetCount(), viewBean)



        if (params.eiFtrCount != null && !"".equals(params.eiFtrCount))
            viewBean.setEiFtrCount(params?.eiFtrCount?.toInteger());

        if (params.eiDetCount != null && !"".equals(params.eiDetCount))
            viewBean.setEiDetCount(params?.eiDetCount?.toInteger());

        if (params.eoFtrCount!=null && !"".equals(params.eoFtrCount))
            viewBean.setEoFtrCount(params?.eoFtrCount?.toInteger());

        if (params.eoDetCount!=null && !"".equals(params.eoDetCount))
            viewBean.setEoDetCount(params?.eoDetCount?.toInteger());

        if (params.eqFtrCount!=null && !"".equals(params.eqFtrCount))
            viewBean.setEqFtrCount(params?.eqFtrCount?.toInteger());

        if (params.eqDetCount!=null && !"".equals(params.eqDetCount))
            viewBean.setEqDetCount(params?.eqDetCount?.toInteger());


        def txFP = countService.countTransactionalFunctions ( viewBean );

        if (params?.af1Count!=null && !"".equals(params?.af1Count))
            viewBean.setAf1Count(params?.af1Count?.toInteger());

        if (params?.af2Count!=null && !"".equals(params?.af2Count))
            viewBean.setAf2Count(params?.af2Count?.toInteger());

        if (params?.af3Count!=null && !"".equals(params?.af3Count))
            viewBean.setAf3Count(params?.af3Count?.toInteger());

        if (params?.af4Count!=null && !"".equals(params?.af4Count))
            viewBean.setAf4Count(params?.af4Count?.toInteger());

        if (params?.af5Count!=null && !"".equals(params?.af5Count))
            viewBean.setAf5Count(params?.af5Count?.toInteger());

        if (params?.af6Count!=null && !"".equals(params?.af6Count))
            viewBean.setAf6Count(params?.af6Count?.toInteger());

        if (params?.af7Count!=null && !"".equals(params?.af7Count))
            viewBean.setAf7Count(params?.af7Count?.toInteger());

        if (params?.af8Count!=null && !"".equals(params?.af8Count))
            viewBean.setAf8Count(params?.af8Count?.toInteger());

        if (params?.af9Count!=null && !"".equals(params?.af9Count))
            viewBean.setAf9Count(params?.af9Count?.toInteger());

        if (params?.af10Count!=null && !"".equals(params?.af10Count))
            viewBean.setAf10Count(params?.af10Count?.toInteger());

        if (params?.af11Count!=null && !"".equals(params?.af11Count))
            viewBean.setAf11Count(params?.af11Count?.toInteger());

        if (params?.af12Count!=null && !"".equals(params?.af12Count))
            viewBean.setAf12Count(params?.af12Count?.toInteger());

        if (params?.af13Count!=null && !"".equals(params?.af13Count))
            viewBean.setAf13Count(params?.af13Count?.toInteger());

        if (params?.af14Count!=null && !"".equals(params?.af14Count))
            viewBean.setAf14Count(params?.af14Count?.toInteger());


        countService.calculateAdjustedFunctionPoints(viewBean)


        getProjects (viewBean);
        //EIF count
//        println ("eifRetCountI:"+eifRetCountI)
//        println ("eifDetCountI:"+eifDetCountI)
//            type = CountService.ComplexityTypes.EIF.name()
//        def eifFPCountI = countService.getFPCount(type ,eifRetCountI, eifDetC ountI)

        println("viewBean:"+viewBean);        render(view: "index", model: [viewBean: viewBean]) //, eifCount:eifFPCountI])
            //, model: [ilfCount: ilfFPCountI ]
//        return [ilfCount: ilfFPCountI , eifCount:eifFPCountI]

    }


    def getProjects (FpaViewBean viewBean){

        def projs = projectsService.allProjects;
        viewBean.setProjects(projs)

    }


    def createProject (){
        println("FPACountController.createProject. Do create project...");

        String projectName= null;
        if (params.projectName!=null && !"".equals(params.projectName))
            projectName=params.projectName;

        println ("Creating:"+projectName);
        projectsService.addProject(projectName);
        return "{\"result\":\"true\"}"
    }


    def deleteProject (){
        Integer id =null;
        if (params.projects!=null && !"".equals(params.projects))
            id=params.projects.toInteger();
        projectsService.removeProject(id);
        return "{\"result\":\"true\"}"
    }




    def getAdjustmentFactors ()
    {
        println ("Params: "+params);

        println ("getAdjustmentFactors...");
        String strIdproj = params.get("id")
        Integer idProject = strIdproj.toInteger();
        def afs = projectsService.getAdjustmentFactors (idProject);
        println ("Factors:"+afs);
        def json ="";
//        def json = new JSON (afs);

        int i=0;
        afs.each {
            if (i!=0)
                json =json+",";
            json= json+"{\"idProject\":\""+it.idProject+"\", \"idQuestion\":\""+it.idQuestion+"\", \"response\": \""+it.response+"\"}";
            i++
        }

//        def res = json.toString();
        def res = "{ \"items\":[ "+json+"]}";
        println (res);

//        render afs as JSON;
        render res;
    }

}


