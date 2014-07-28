package FPA_Webapp

import FPA_Webapp_G237.ComplexityMatrix
import FPA_Webapp_G237.CplxTranslation
import FPA_Webapp_G237.Functionality
import FPA_Webapp_G237.Projects
import core.Calculations
import core.FpaViewBean
import org.springframework.transaction.annotation.Transactional


@Transactional
class CountService {

    def serviceMethod() {

    }

    enum ComplexityTypes {
        ILF, EIF, EI, EOF, EO, EQ, EOEQ, ILFEIF
    }

    public String [] transactionalFunctions = ["EI","EO", "EQ"];
    public String [] dataFunctions =   ["EIF","ILF"];

    def getComplexityFromMatrixCount(String tp, Integer hcount, Integer vcount, FpaViewBean viewBean) {
        Integer result = 0;

        println("tp:" + tp);
        println("hcount:" + hcount);
        println("vcount:" + vcount);

        if (tp == null || hcount == null || vcount == null) {
            return null;
        }


        String typeType;
        typeType = decodeComplexityMatrix(tp);

        println("typeType:" + typeType);
//        println ("Query:"+query.toString())
        Integer complexity;
        //Collection cms =Complexitymatrix.executeQuery( ' Select distinct id from Complexitymatrix c  where c.type= :type  and ((c.horizontallowlimit<=:hcount and c.horizontaltoplimit>= :hcount ) or (c.horizontallowlimit IS NULL  and c.horizontaltoplimit>= :hcount ) or ( c.horizontallowlimit<= :hcount and c.horizontaltoplimit IS NULL )  and ((c.verticallowlimit<=:vcount and c.verticaltoplimit>= :vcount ) or (c.verticallowlimit IS NULL  and c.verticaltoplimit>= :vcount ) or ( c.verticallowlimit<= :vcount and c.verticaltoplimit IS NULL )  ) )',[ type:typeType, hcount: hcount,  vcount: vcount]) //.findAll() ,
        def params = [type: typeType, hcount: hcount, vcount: vcount]
        println("Params:" + params)
        Collection cms = ComplexityMatrix.executeQuery(' Select distinct id from FPA_Webapp_G237.ComplexityMatrix c  ' +
                ' where c.type= :type  and ((c.horizontalLowLimit<=:hcount and c.horizontalTopLimit>= :hcount ) or (c.horizontalLowLimit<0  and c.horizontalTopLimit>= :hcount ) or ( c.horizontalLowLimit<= :hcount and c.horizontalTopLimit<0 ) ) and ((c.verticalLowLimit<=:vcount and c.verticalTopLimit>= :vcount ) or (c.verticalLowLimit <0 and c.verticalTopLimit>= :vcount ) or ( c.verticalLowLimit<= :vcount and c.verticalTopLimit<0)  ) ', params)
        //.findAll() ,
        println("Result:" + cms);

        Integer id = null;
        cms.each {
            println(it);
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
        println("FindingById id:" + id);
//        cmId = findById (id);
        cmId = findComplexityById(id?.intValue());
        complexity = cmId?.complexity
        println("FindbyId:" + cmId);
        println("complexity:" + complexity);
        result = translateComplexity(tp, complexity)

        if (viewBean != null) {

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
        }
        return result;
    }


    ComplexityMatrix findById(Integer idCM) {
        println("id:" + idCM);
        def query = ComplexityMatrix.where {
            eq("id", idCM)
        }

        def elem = query.find();
        println("ById:" + elem);
        return elem;
    }

    ComplexityMatrix findComplexityById(Integer cpxId) {
        ComplexityMatrix complexity = null;
        if (cpxId != null) {
            Collection cms = ComplexityMatrix.executeQuery(' Select c from FPA_Webapp_G237.ComplexityMatrix c  where c.id=:id ', [id: cpxId?.longValue()]);
            println("Result: " + cms);
            complexity = cms.get(0)
        }
        return complexity;
    }


    def translateComplexity(String type, Integer complexityId) {
        println("Type:" + type)
        println("complexityId:" + complexityId)

        String dbType = decodeTranslation(type);

        def query = CplxTranslation.where {
            type == dbType && complexity == complexityId
        }

        CplxTranslation translated = query.find();
        println("Translated:" + translated?.ufp)
        return translated?.ufp;
    }

    def decodeComplexityMatrix(String type) {
        String result;
        switch (type) {
            case "ILF":
                result = "ILFEIF";
                break;
            case "EIF":
                result = "ILFEIF";
                break;
            case "EO":
                result = "EOEQ"
                break;
            case "EQ":
                result = "EOEQ"
                break;
            default:
                result = type;
                break;
        }

        return result;
    }

    def decodeTranslation(String type) {
        String result;

        switch (type) {
            case "EI":
                result = "EIEQ";
                break;
            case "EQ":
                result = "EIEQ";
                break;
            default:
                result = type;
                break;
        }


        return result;
    }


    def calculateConversionContribution(FpaViewBean viewBean) {
        //TODO
        println("TODO");
        return 0;
    }

    Integer countTransactionalFunctions(FpaViewBean result) {
        // Integer eiFtr, Integer eiDet, Integer eoFtr, Integer eoDet, Integer eqFtr, Integer eqDet,
        Integer eiFp = this.getComplexityFromMatrixCount(ComplexityTypes.EI.name(), result.getEiFtrCount(), result.getEiDetCount(), result)
        println("eiFP:" + eiFp);
        result.setEiCount(eiFp)

        Integer eoFp = this.getComplexityFromMatrixCount(ComplexityTypes.EO.name(), result.getEoFtrCount(), result.getEoDetCount(), result)
        println("eoFp:" + eoFp);
        result.setEoCount(eoFp)

        Integer eqFp = this.getComplexityFromMatrixCount(ComplexityTypes.EQ.name(), result.getEqFtrCount(), result.getEoDetCount(), result)
        println("eqFp:" + eqFp);
        result.setEqCount(eqFp)

        Integer sum = 0;
        if (eiFp != null)
            sum += eiFp

        if (eoFp != null)
            sum += eoFp

        if (eqFp != null)
            sum += eqFp

        return sum
    }


    def calculateUnAdjustedFPs(FpaViewBean viewBean) {
        Integer sum = 0;
        if (viewBean != null) {

            sum += viewBean?.getIlfCount() != null ? viewBean?.getIlfCount() : 0
            sum += viewBean?.getEifCount() != null ? viewBean?.getEifCount() : 0
            sum += viewBean?.getEiCount() != null ? viewBean?.getEiCount() : 0
            sum += viewBean?.getEoCount() != null ? viewBean?.getEoCount() : 0
            sum += viewBean?.getEqCount() != null ? viewBean?.getEqCount() : 0

            viewBean.setUnadjustedFps(sum)
        }

        return sum;
    }


    def calculateAdjustmentFactor(FpaViewBean viewBean) {
        //vaF= (TDI *0.01)+0.65
        def res = 0;
        def vaf = 0;
        if (viewBean != null) {
            res += viewBean?.af1Count != null ? viewBean?.af1Count : 0
            res += viewBean?.af2Count != null ? viewBean?.af2Count : 0
            res += viewBean?.af3Count != null ? viewBean?.af3Count : 0
            res += viewBean?.af4Count != null ? viewBean?.af4Count : 0
            res += viewBean?.af5Count != null ? viewBean?.af5Count : 0
            res += viewBean?.af6Count != null ? viewBean?.af6Count : 0
            res += viewBean?.af7Count != null ? viewBean?.af7Count : 0
            res += viewBean?.af8Count != null ? viewBean?.af8Count : 0
            res += viewBean?.af9Count != null ? viewBean?.af9Count : 0
            res += viewBean?.af10Count != null ? viewBean?.af10Count : 0
            res += viewBean?.af11Count != null ? viewBean?.af11Count : 0
            res += viewBean?.af12Count != null ? viewBean?.af12Count : 0
            res += viewBean?.af13Count != null ? viewBean?.af13Count : 0
            res += viewBean?.af14Count != null ? viewBean?.af14Count : 0

        }

        vaf = (res * 0.01) + 0.65
        return vaf;
    }


    def calculateAdjustedFunctionPoints(FpaViewBean viewBean) {

        //DFP = (UFP +CFP )*VAF
/*
        result = (unadjustedFP + ConversionContribution (==0?))* FPA_Webapp_G237.AdjustmentFactor.FPA_Webapp_G237.AdjustmentFactor

        result = (unadjustedFP )* FPA_Webapp_G237.AdjustmentFactor.FPA_Webapp_G237.AdjustmentFactor

        TDI = res cuestionario

        vaF= (TDI *0.01)+0.65

        Float result = (TDI *0.01)+0.65
*/
        def fps = null
        if (viewBean != null) {

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

    Calculations calculateProjectFps(Integer idProject) {
        println("ProjectId:" + idProject);

        Projects proj = Projects.get(idProject);

        Calculations calcs= new Calculations ();
        def afps = calculateAdjFactor(proj, calcs);

        calcs.setEstimateInDays(afps/proj.productivity);

        calcs.setEstimateInHours(calcs.getEstimateInDays()*8);

        calcs.setEstimatedFinishDate(calculateEstimateFinishDate(calcs));
        return calcs;

    }



    private static boolean isBusinessDay(Calendar dateToCheck, List<Integer> daysToExclude){
        for(Integer dayToExclude : daysToExclude){
            if(dayToExclude != null && dayToExclude == dateToCheck.get(Calendar.DAY_OF_WEEK)) {
                return true;
            }
            else continue;
        }
        return false;
    }

    Date calculateEstimateFinishDate (Calculations calcs)
    {
//        Date today = new Date();

        List<Integer> nonWorkingDays = new ArrayList<Integer>();
        nonWorkingDays.add(Calendar.SATURDAY);
        nonWorkingDays.add(Calendar.SUNDAY);

        Calendar  finishDate = Calendar.getInstance();
        Integer hours = calcs.getEstimateInHours();
        while (hours >0)
        {

            if (isBusinessDay (finishDate,nonWorkingDays )) {

                if (finishDate.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY)
                    hours -= 7;
                else
                    hours -=8;
            }
            finishDate.add(Calendar.DATE, 1);
        }
        return new Date (finishDate.getTimeInMillis());
    }

    Integer calculateFunctionalityFps(Collection<Functionality> functionalities) {
        Integer res = 0;
        functionalities.each {
            res += getComplexityFromMatrixCount(it.type, it.hcount, it.vcount, null);
        };
        return res;
    }



   private Set<Functionality> filter (Set<Functionality> functs , String[] types)
   {

       Set<Functionality> filtered = new HashSet<Functionality> ();

       functs.each {
           for (String type : types)
           {
               if (type== it.type)
                   filtered.add(it);
           }
       }
       return filtered;
   }



    Float calculateAdjFactor(Projects proj, Calculations calcs) {
        float adjustedFps = 0;

        if (proj != null) {
            Integer txUfps = calculateFunctionalityFps (filter (proj.functionalities,transactionalFunctions));
            Integer dataUfps = calculateFunctionalityFps (filter (proj.functionalities,dataFunctions));

            calcs.setDataFunctions(dataUfps);
            calcs.setTransactionalFunctions(txUfps);


            Integer ufps = calculateFunctionalityFps(proj.functionalities);

            Integer res = 0;
            proj.getAdjustmentFactors().each {
                res = res + it.response;
            }

            def vaf = (res * 0.01) + 0.65
            adjustedFps = ufps * vaf;
            calcs.setAdjustmentFactor(vaf);
            calcs.setUfps(ufps);
        }

        calcs.setAfps(adjustedFps);

        return adjustedFps;
    }
}


