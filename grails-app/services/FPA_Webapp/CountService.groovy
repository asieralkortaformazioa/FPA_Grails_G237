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


    Integer countTransactionalFunctions( Integer eiFtr, Integer eiDet, Integer eoFtr, Integer eoDet, Integer eqFtr, Integer eqDet, FpaViewBean result )
    {
        Integer eiFp= this.getComplexityFromMatrixCount (ComplexityTypes.EI.name(), eiFtr, eiDet, result)
        println ("eiFP:"+eiFp);
        result.setEiCount(eiFp)

        Integer eoFp = this.getComplexityFromMatrixCount (ComplexityTypes.EO.name(), eoFtr, eoDet, result)
        println ("eoFp:"+eoFp);
        result.setEoCount(eoFp)

        Integer eqFp = this.getComplexityFromMatrixCount (ComplexityTypes.EQ.name(), eqFtr, eqDet, result)
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

            sum += viewBean.getIlfCount()
            sum += viewBean.getEifCount()
            sum += viewBean.getEiCount()
            sum += viewBean.getEoCount()
            sum += viewBean.getEqCount()

        }
        return sum;
    }


}
