package FPA_Webapp

class FPACountController {

    def index() {
        redirect (action : "showCount", params: params)
//        redirect (action : "doCount", params: params)fi
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
        println ("doCount");
        def countService = new CountService();

        //ILF count
//        def ilfRetCount = params.ilfRetCount; //params.get('ilfRetCount')
//        def ilfDetCount = params.ilfDetCount ; //get('ilfDetCount')

        println ("params:"+params.get('ilfRetCount'));
        println ("params:"+params.ilfRetCount);
        println ("type:"+CountService.ComplexityTypes.ILF.name())
//        println ("ilfRetCount:"+ilfRetCount)
//        println ("ilfDetCount:"+ilfDetCount)
        println("params:"+params)
        println("countService:"+countService)

        Integer ilfRetCountI;
        if (params.ilfRetCount!=null)
            ilfRetCountI= params.ilfRetCount.toInteger();

        Integer ilfDetCountI;
        if (params.ilfDetCount!=null)
            ilfDetCountI= params.ilfDetCount.toInteger();

        Integer eifRetCountI;
        if (params.eifRetCount!=null)
            eifRetCountI= params.eifRetCount.toInteger();

        Integer eifDetCountI;
        if (params.eifDetCount!=null)
            eifDetCountI= params.eifDetCount.toInteger();

        println ("ilfRetCountI:"+ilfRetCountI)
        println ("ilfDetCountI:"+ilfDetCountI)
        String type = CountService.ComplexityTypes.ILF.name()

        println ("type:"+type)
        def ilfFPCountI = countService.getFPCount(type, ilfRetCountI, ilfDetCountI)

        //EIF count
//        println ("eifRetCountI:"+eifRetCountI)
//        println ("eifDetCountI:"+eifDetCountI)
//            type = CountService.ComplexityTypes.EIF.name()
//        def eifFPCountI = countService.getFPCount(type ,eifRetCountI, eifDetC ountI)

        render(view: "index") //, eifCount:eifFPCountI])
            //, model: [ilfCount: ilfFPCountI ]
//        return [ilfCount: ilfFPCountI , eifCount:eifFPCountI]

    }

}
