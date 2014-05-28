package FPA_Webapp

import FPA_Webapp_G237.ComplexityMatrix
import core.FpaViewBean

class FPACountController {

    def index() {
        redirect (action : "showCount", params: params)
//        redirect (action : "doCount", params: params)
    }
    //static allowedMethods = [ showcount: "GET" , doCount:"POST" ]

    def showCount = {
        println ("showCount");
        render(view: "index", model: [ilfCount: 0, eifCount : 0])
    }

    def doCount ()
//    (@RequestParameter ('ilfRetCount ')  String  ilfRetCount, @RequestParameter ('ilfDetCount ')  String  ilfDetCount,
//                 @RequestParameter ('eifRetCount ')  String  eifRetCount, @RequestParameter ('eifDetCount ')  String  eifDetCount)
    {
        println("doCount");
        def countService = new CountService();

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


        FpaViewBean result = new FpaViewBean();



        if (params.ilfRetCount != null && !"".equals(params.ilfRetCount))
            result.setIlfRetCount(params?.ilfRetCount?.toInteger());



        if (params.ilfDetCount != null && !"".equals(params.ilfDetCount))
            result.setIlfDetCount (params?.ilfDetCount?.toInteger());



        if (params.eifRetCount != null && !"".equals(params.eifRetCount))
            result.setEifRetCount(params?.eifRetCount?.toInteger());



        if (params.eifDetCount != null && !"".equals(params.eifDetCount))
            result.setEifDetCount(params?.eifDetCount?.toInteger());



        String type = CountService.ComplexityTypes.ILF.name()

        println("type:" + type)


        println("Counting data functions: ")
        def ilfFPCountI = countService.getComplexityFromMatrixCount(CountService.ComplexityTypes.ILF.name(), result.getIlfRetCount(), result.getIlfDetCount(), result)

        def eifFPCountI = countService.getComplexityFromMatrixCount(CountService.ComplexityTypes.EIF.name (), result.getIlfRetCount(), result.getIlfDetCount(), result)



        if (params.eiFtrCount != null && !"".equals(params.eiFtrCount))
            result.setEiFtrCount(params?.eiFtrCount?.toInteger());

        if (params.eiDetCount != null && !"".equals(params.eiDetCount))
            result.setEiDetCount(params?.eiDetCount?.toInteger());

        if (params.eoFtrCount!=null && !"".equals(params.eoFtrCount))
            result.setEoFtrCount(params?.eoFtrCount?.toInteger());

        if (params.eoDetCount!=null && !"".equals(params.eoDetCount))
            result.setEoDetCount(params?.eoDetCount?.toInteger());

        if (params.eqFtrCount!=null && !"".equals(params.eqFtrCount))
            result.setEqFtrCount(params?.eqFtrCount?.toInteger());

        if (params.eqDetCount!=null && !"".equals(params.eqDetCount))
            result.setEqDetCount(params?.eqDetCount?.toInteger());


        def txFP = countService.countTransactionalFunctions ( result );

        if (params?.af1Count!=null && !"".equals(params?.af1Count))
            result.setAf1Count(params?.af1Count?.toInteger());

        if (params?.af2Count!=null && !"".equals(params?.af2Count))
            result.setAf2Count(params?.af2Count?.toInteger());

        if (params?.af3Count!=null && !"".equals(params?.af3Count))
            result.setAf3Count(params?.af3Count?.toInteger());

        if (params?.af4Count!=null && !"".equals(params?.af4Count))
            result.setAf4Count(params?.af4Count?.toInteger());

        if (params?.af5Count!=null && !"".equals(params?.af5Count))
            result.setAf5Count(params?.af5Count?.toInteger());

        if (params?.af6Count!=null && !"".equals(params?.af6Count))
            result.setAf6Count(params?.af6Count?.toInteger());

        if (params?.af7Count!=null && !"".equals(params?.af7Count))
            result.setAf7Count(params?.af7Count?.toInteger());

        if (params?.af8Count!=null && !"".equals(params?.af8Count))
            result.setAf8Count(params?.af8Count?.toInteger());

        if (params?.af9Count!=null && !"".equals(params?.af9Count))
            result.setAf9Count(params?.af9Count?.toInteger());

        if (params?.af10Count!=null && !"".equals(params?.af10Count))
            result.setAf10Count(params?.af10Count?.toInteger());

        if (params?.af11Count!=null && !"".equals(params?.af11Count))
            result.setAf11Count(params?.af11Count?.toInteger());

        if (params?.af12Count!=null && !"".equals(params?.af12Count))
            result.setAf12Count(params?.af12Count?.toInteger());

        if (params?.af13Count!=null && !"".equals(params?.af13Count))
            result.setAf13Count(params?.af13Count?.toInteger());

        if (params?.af14Count!=null && !"".equals(params?.af14Count))
            result.setAf14Count(params?.af14Count?.toInteger());


        countService.calculateAdjustedFunctionPoints(result)
        //EIF count
//        println ("eifRetCountI:"+eifRetCountI)
//        println ("eifDetCountI:"+eifDetCountI)
//            type = CountService.ComplexityTypes.EIF.name()
//        def eifFPCountI = countService.getFPCount(type ,eifRetCountI, eifDetC ountI)

        println("viewBean:"+result);        render(view: "index", model: [viewBean: result]) //, eifCount:eifFPCountI])
            //, model: [ilfCount: ilfFPCountI ]
//        return [ilfCount: ilfFPCountI , eifCount:eifFPCountI]

    }

}
