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


        def txFP = countService.countTransactionalFunctions ( result.getEiFtrCount(), result.getEiDetCount(), result.getEoFtrCount(),result.getEoDetCount(), result.getEqFtrCount(), result.getEqDetCount(), result );




        //EIF count
//        println ("eifRetCountI:"+eifRetCountI)
//        println ("eifDetCountI:"+eifDetCountI)
//            type = CountService.ComplexityTypes.EIF.name()
//        def eifFPCountI = countService.getFPCount(type ,eifRetCountI, eifDetC ountI)

        println("viewBean:"+result);
        render(view: "index", model: [viewBean: result]) //, eifCount:eifFPCountI])
            //, model: [ilfCount: ilfFPCountI ]
//        return [ilfCount: ilfFPCountI , eifCount:eifFPCountI]

    }

}
