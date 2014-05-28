package FPA_Webapp

import FPA_Webapp_G237.ComplexityMatrix
import FPA_Webapp_G237.CplxTranslation
import core.FpaViewBean
import org.springframework.transaction.annotation.Transactional


@Transactional
class CountService {

    def serviceMethod() {

    }

    enum ComplexityTypes {ILF, EIF, EI , EO, EQ, EOEQ, ILFEIF}


    def getComplexityFromMatrixCount ( String tp, Integer hcount, Integer vcount, FpaViewBean viewBean)
    {
        Integer result= 0;

        println("tp:"+tp);
        println("hcount:"+hcount);
        println("vcount:"+vcount);

        if (tp==null || hcount ==null || vcount ==null)
        {
            return null;
        }


        String typeType ;
        typeType= decodeComplexityMatrix(tp);

        println("typeType:"+typeType);
//        println ("Query:"+query.toString())
        Integer complexity;
        //Collection cms =Complexitymatrix.executeQuery( ' Select distinct id from Complexitymatrix c  where c.type= :type  and ((c.horizontallowlimit<=:hcount and c.horizontaltoplimit>= :hcount ) or (c.horizontallowlimit IS NULL  and c.horizontaltoplimit>= :hcount ) or ( c.horizontallowlimit<= :hcount and c.horizontaltoplimit IS NULL )  and ((c.verticallowlimit<=:vcount and c.verticaltoplimit>= :vcount ) or (c.verticallowlimit IS NULL  and c.verticaltoplimit>= :vcount ) or ( c.verticallowlimit<= :vcount and c.verticaltoplimit IS NULL )  ) )',[ type:typeType, hcount: hcount,  vcount: vcount]) //.findAll() ,
        def params= [ type:typeType, hcount: hcount,  vcount: vcount]
        println("Params:"+params)
        Collection cms =ComplexityMatrix.executeQuery( ' Select distinct id from ComplexityMatrix c  where c.type= :type  and ((c.horizontalLowLimit<=:hcount and c.horizontalTopLimit>= :hcount ) or (c.horizontalLowLimit<0  and c.horizontalTopLimit>= :hcount ) or ( c.horizontalLowLimit<= :hcount and c.horizontalTopLimit<0 )  and ((c.verticalLowLimit<=:vcount and c.verticalTopLimit>= :vcount ) or (c.verticalLowLimit <0 and c.verticalTopLimit>= :vcount ) or ( c.verticalLowLimit<= :vcount and c.verticalTopLimit<0)  ) )',params ) //.findAll() ,
        println("Result:"+cms);

        Integer  id = null;
        cms.each {
            println (it);
//            complexity = it.complexity;
            id = it;
        }

//        def query2 = Complexitymatrix.where {
//            and {
//                eq('id', id)
//                eq('type',typeType)
//            }
//        }
//        Collection <Complexitymatrix> cmId=  query2.findAll()
        // findById (columnName = ID )
        //def cmId = Complexitymatrix.findById(id)
        def cmId = null
//        def example = new Complexitymatrix()
//        example.setId(id)
//        cmId =Complexitymatrix.get("6")
//        cmId =Complexitymatrix.findById(""+id)
        println ("FindingById id:"+id);
//        cmId = findById (id);
        cmId = findComplexityById (id?.intValue());
        complexity= cmId?.complexity
        println ("FindbyId:"+cmId);
        println ("complexity:"+complexity);
        result =translateComplexity (tp, complexity)

        switch (tp) {
            case "ILF":
                viewBean.setIlfCount(result)
                break;
            case "EIF":
                viewBean.setEifCount(result)
                break;
            default:
                break;
        }

        return result;
    }


    ComplexityMatrix findById (Integer idCM){
        println ("id:"+idCM);
        def query = ComplexityMatrix.where {
            eq ("id", idCM)
        }

        def elem =  query.find();
        println ("ById:"+elem);
        return elem;
    }

    ComplexityMatrix findComplexityById (Integer cpxId) {
        ComplexityMatrix complexity = null;
        if (cpxId != null) {
            Collection cms =ComplexityMatrix.executeQuery( ' Select c from ComplexityMatrix c  where c.id=:id ' , [id:cpxId?.longValue()]);
            println("Result: "+cms);
            complexity = cms.get(0)
        }
        return complexity;
    }


    def translateComplexity (String type, Integer complexityId)
    {
        println ("Type:"+type)
        println ("complexityId:"+complexityId)

        String dbType = decodeTranslation (type);

        def query = CplxTranslation.where {
            type== dbType && complexity==complexityId
        }

        CplxTranslation translated =  query.find();
        println ("Translated:"+translated?.ufp)
        return translated?.ufp;
    }

    def decodeComplexityMatrix (String type)
    {
            String result ;
        switch (type) {
            case "ILF":
                result="ILFEIF";
                break;
            case "EIF":
                result="ILFEIF";
                break;
            case "EO":
                result= "EOEQ"
                break;
            case "EQ":
                result= "EOEQ"
                break;
            default:
                result=type;
                break;
        }

       return result;
    }

    def decodeTranslation ( String type)
    {
        String result ;

        switch (type)
        {
            case "EI":
                result="EIEQ";
            break;
            case "EQ":
                result="EIEQ";
            break;
            default:
                result = type;
            break;
        }


        return result;
    }



    def calculateConversionContribution (FpaViewBean viewBean)
    {
        //TODO
        println ("TODO");
        return 0;
    }

    Integer countTransactionalFunctions( FpaViewBean result )
    {
        // Integer eiFtr, Integer eiDet, Integer eoFtr, Integer eoDet, Integer eqFtr, Integer eqDet,
        Integer eiFp= this.getComplexityFromMatrixCount (ComplexityTypes.EI.name(), result.getEiFtrCount(), result.getEiDetCount(), result)
        println ("eiFP:"+eiFp);
        result.setEiCount(eiFp)

        Integer eoFp = this.getComplexityFromMatrixCount (ComplexityTypes.EO.name(), result.getEoFtrCount(), result.getEoDetCount(), result)
        println ("eoFp:"+eoFp);
        result.setEoCount(eoFp)

        Integer eqFp = this.getComplexityFromMatrixCount (ComplexityTypes.EQ.name(), result.getEqFtrCount(), result.getEoDetCount(), result)
        println ("eqFp:"+eqFp);
        result.setEqCount(eqFp)

        Integer sum = 0;
        if (eiFp!=null)
            sum += eiFp

        if (eoFp!=null)
            sum += eoFp

        if (eqFp!=null)
            sum += eqFp

        return sum
    }


    def calculateUnAdjustedFPs (FpaViewBean viewBean)
    {
        Integer sum = 0;
        if (viewBean!=null)
        {

            sum += viewBean?.getIlfCount()!=null?viewBean?.getIlfCount():0
            sum += viewBean?.getEifCount()!=null?viewBean?.getEifCount():0
            sum += viewBean?.getEiCount()!=null?viewBean?.getEiCount():0
            sum += viewBean?.getEoCount()!=null?viewBean?.getEoCount():0
            sum += viewBean?.getEqCount()!=null?viewBean?.getEqCount():0

            viewBean.setUnadjustedFps(sum)
        }

        return sum;
    }


    def calculateAdjustmentFactor (FpaViewBean viewBean)
    {
        //vaF= (TDI *0.01)+0.65
        def res =0;
        def vaf =0;
        if (viewBean !=null)
        {
            res += viewBean?.af1Count!=null?viewBean?.af1Count:0
            res += viewBean?.af2Count!=null?viewBean?.af2Count:0
            res += viewBean?.af3Count!=null?viewBean?.af3Count:0
            res += viewBean?.af4Count!=null?viewBean?.af4Count:0
            res += viewBean?.af5Count!=null?viewBean?.af5Count:0
            res += viewBean?.af6Count!=null?viewBean?.af6Count:0
            res += viewBean?.af7Count!=null?viewBean?.af7Count:0
            res += viewBean?.af8Count!=null?viewBean?.af8Count:0
            res += viewBean?.af9Count!=null?viewBean?.af9Count:0
            res += viewBean?.af10Count!=null?viewBean?.af10Count:0
            res += viewBean?.af11Count!=null?viewBean?.af11Count:0
            res += viewBean?.af12Count!=null?viewBean?.af12Count:0
            res += viewBean?.af13Count!=null?viewBean?.af13Count:0
            res += viewBean?.af14Count!=null?viewBean?.af14Count:0

        }

        vaf = (res * 0.01) + 0.65
        return vaf;
    }


    def calculateAdjustedFunctionPoints (FpaViewBean viewBean)
    {

        //DFP = (UFP +CFP )*VAF
/*
        result = (unadjustedFP + ConversionContribution (==0?))* AdjustmentFactor

        result = (unadjustedFP )* AdjustmentFactor

        TDI = res cuestionario

        vaF= (TDI *0.01)+0.65

        Float result = (TDI *0.01)+0.65
*/
        def fps =null
        if (viewBean!=null) {

            if (viewBean?.getUnadjustedFps() == null)
                calculateUnAdjustedFPs(viewBean)

            def unadjustedFPs = viewBean.getUnadjustedFps();
            def adjFactor = calculateAdjustmentFactor(viewBean);
            def conversionContribution = calculateConversionContribution(viewBean);
            fps = (unadjustedFPs + (conversionContribution != null ? conversionContribution : 0)) * adjFactor;
            viewBean?.setAdjustedFps(fps)
        }
        return fps;
    }


}

