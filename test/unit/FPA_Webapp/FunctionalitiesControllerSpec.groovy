package FPA_Webapp

import core.FunctionTypes
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(FunctionalitiesController)
class FunctionalitiesControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test creation"() {
        when:
            params.description ="testFunct"
            params.type= "EIF"
            params.hCount= 5
            params.vCount =6
            params.idProj=1
            controller.create()
        then:
            response.text !=null && !"".equals(response.text)

    }
}
