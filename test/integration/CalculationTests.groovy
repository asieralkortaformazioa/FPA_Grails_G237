import FPA_Webapp.CountService
import FPA_Webapp.ProjectService
import FPA_Webapp_G237.Functionality
import FPA_Webapp_G237.Projects
import junit.framework.Assert
import org.junit.Test

/**
 * Created by developer on 7/23/14.
 */

class CalculationTests extends GroovyTestCase{

    CountService  countService = new CountService ();
    ProjectService projectService = new ProjectService ();
    @Test
public void testDataFunctionCalculations ()
    {
        Projects p = projectService.getProject(19);
        org.junit.Assert.assertNotNull(p);

        Set <Functionality> dataFunctions = countService.filter(p.functionalities, countService.dataFunctions);

        Set <Functionality> txFunctions =countService.filter(p.functionalities, countService.transactionalFunctions);


        Integer dataFps= countService.calculateFunctionalityFps (dataFunctions);

        Integer txFps= countService.calculateFunctionalityFps (txFunctions);

        Assert.assertTrue(dataFps==7);

        Assert.assertTrue(txFps>1);


    }

@Test
public void testFilter () {

    Projects p = projectService.getProject(19);

    Set <Functionality> dataFunctions = countService.filter(p.functionalities, countService.dataFunctions);
    Assert.assertTrue(dataFunctions!=null && dataFunctions.size()==1);

    Set <Functionality> txFunctions =countService.filter(p.functionalities, countService.transactionalFunctions);
    Assert.assertTrue(txFunctions!=null && txFunctions.size()>1);
}
}
