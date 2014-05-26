package FPA_Webapp

import FPA_Webapp_G237.ComplexityMatrix
import FPA_Webapp_G237.CplxTranslation
import org.springframework.transaction.annotation.Transactional


@Transactional
class CountService {

    def serviceMethod() {

    }

    enum ComplexityTypes {ILF, EIF}


    def getFPCount ( String tp, Integer hcount, Integer vcount)
    {
        Integer result= 0;
        println("tp:"+tp);
        println("hcount:"+hcount);
        println("vcount:"+vcount);

        String typeType ;
        switch (tp) {
        case "ILF":
            typeType="ILFEIF";
            break;
         case "EIF":
             typeType="ILFEIF";
             break;
         default:
            break;
        }

        println("typeType:"+typeType);
//        def query = Complexitymatrix.where {
//            and {
//                eq("type", typeType)

//                and {
//                    or {
//
//                        and {
//
//                            le("horizontallowlimit", hcount)
//
//                            ge("horizontaltoplimit", hcount)
//
//                        }
//                        and {
//                            le("horizontallowlimit", hcount)
//
//                            isNull("horizontaltoplimit")
//                        }
//
//                        and {
//                            isNull("horizontallowlimit")
//
//                            ge("horizontaltoplimit", hcount)
//                        }
//
//                    }

//                    or {
//                        and {
//                            le("verticallowlimit", vcount)
//
//                            ge("verticaltoplimit", vcount)
//                        }
//
//                        and {
//                            le("verticallowlimit", vcount)
//
//                            isNull("verticaltoplimit")
//                        }
//
//                        and {
//                            isNull("verticallowlimit")
//
//                            ge("verticaltoplimit", vcount)
//                        }
//                }

//                }

//            }

//        }
//        println ("Query:"+query.toString())
        Integer complexity;
        //Collection cms =Complexitymatrix.executeQuery( ' Select distinct id from Complexitymatrix c  where c.type= :type  and ((c.horizontallowlimit<=:hcount and c.horizontaltoplimit>= :hcount ) or (c.horizontallowlimit IS NULL  and c.horizontaltoplimit>= :hcount ) or ( c.horizontallowlimit<= :hcount and c.horizontaltoplimit IS NULL )  and ((c.verticallowlimit<=:vcount and c.verticaltoplimit>= :vcount ) or (c.verticallowlimit IS NULL  and c.verticaltoplimit>= :vcount ) or ( c.verticallowlimit<= :vcount and c.verticaltoplimit IS NULL )  ) )',[ type:typeType, hcount: hcount,  vcount: vcount]) //.findAll() ,
        Collection cms =ComplexityMatrix.executeQuery( ' Select distinct id from ComplexityMatrix c  where c.type= :type  and ((c.horizontalLowLimit<=:hcount and c.horizontalTopLimit>= :hcount ) or (c.horizontalLowLimit<0  and c.horizontalTopLimit>= :hcount ) or ( c.horizontalLowLimit<= :hcount and c.horizontalTopLimit<0 )  and ((c.verticalLowLimit<=:vcount and c.verticalTopLimit>= :vcount ) or (c.verticalLowLimit <0 and c.verticalTopLimit>= :vcount ) or ( c.verticalLowLimit<= :vcount and c.verticalTopLimit<0)  ) )',[ type:typeType, hcount: hcount,  vcount: vcount]) //.findAll() ,
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
        return result;
    }


    ComplexityMatrix findById (Integer idCM){
        println ("id:"+idCM);
        def query = ComplexityMatrix.where {
            eq ("ID", idCM)
        }

        def elem =  query.find();
        println ("ById:"+elem);
        return elem;
    }

    Integer findComplexityById (Integer  cpxId) {
        Integer complexity = null;
        if (cpxId != null) {
            Collection cms =ComplexityMatrix.executeQuery( ' Select distinct id from ComplexityMatrix c  where c.id=:id ' , [id:cpxId]);
            complexity = cms.get(0)
        }
        return complexity;
    }


    def translateComplexity (String type, Integer complexityId)
    {
        println ("Type:"+type)
        println ("complexityId:"+complexityId)

        def query = CplxTranslation.where {
            type== type && complexity==complexityId
        }

        CplxTranslation translated =  query.find();
        println ("Translated:"+translated?.ufp)
        return translated?.ufp;

    }

}
