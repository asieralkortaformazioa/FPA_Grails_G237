import FPA_Webapp.CountService
import core.FpaViewBean
import org.springframework.beans.factory.annotation.Autowired

import static org.testng.Assert.*


import org.testng.annotations.Test

/**
 * Created by developer on 28/05/14.
 */
class TestCountService {


    CountService countService = new CountService();


    @Test
    public def testCalculateUnadjustedFps ()
    {
        def viewBean = getViewBean();
        def res = countService.calculateUnAdjustedFPs(viewBean);
        println("Res:"+res);
        assertNotNull(res)
        assertTrue(res>0)
    }



    @Test
    public void testCalculateAdjustmentFactor() throws Exception {
        def viewBean = getViewBean();
        def adjFactor= countService.calculateAdjustmentFactor(viewBean)
        println ("adjFactor:"+adjFactor);
        assertNotNull(adjFactor)
        assertTrue(adjFactor>0)

        //countService.calculateAdjustedFunctionPoints(viewBean)

    }


    @Test
    public void testCalculateAdjustedFunctionPoints() throws Exception {
        def viewBean = getViewBean();
        countService.calculateAdjustedFunctionPoints();

        def adjFactor= countService.calculateAdjustedFunctionPoints(viewBean)
        println ("adjFactor:"+adjFactor);
        assertNotNull(adjFactor)
        assertTrue(adjFactor>0)

    }


    private def getViewBean ()
    {
        FpaViewBean viewBean = new FpaViewBean();

        // Calculate UFps
        viewBean.setIlfDetCount(1);
        viewBean.setIlfRetCount(6);
        viewBean.setEifDetCount(2);
        viewBean.setEifRetCount(7);

        viewBean.setEiFtrCount(2);
        viewBean.setEiDetCount(5);

        viewBean.setEoFtrCount(2);
        viewBean.setEoDetCount(5);

        viewBean.setEqFtrCount(2);
        viewBean.setEqDetCount(5);

        //FPA_Webapp_G237.FPA_Webapp_G237.FPA_Webapp_G237.FPA_Webapp_G237.FPA_Webapp_G237.FPA_Webapp_G237.AdjustmentFactor.FPA_Webapp_G237.FPA_Webapp_G237.FPA_Webapp_G237.FPA_Webapp_G237.AdjustmentFactor
        viewBean.setAf1Count(1);
        viewBean.setAf2Count(2);
        viewBean.setAf3Count(3);
        viewBean.setAf4Count(4);
        viewBean.setAf5Count(5);
        viewBean.setAf6Count(0);
        viewBean.setAf7Count(1);
        viewBean.setAf8Count(2);
        viewBean.setAf9Count(3);
        viewBean.setAf10Count(4);
        viewBean.setAf11Count(5);
        viewBean.setAf12Count(0);
        viewBean.setAf13Count(1);
        viewBean.setAf14Count(2);

        return viewBean;
    }


}

