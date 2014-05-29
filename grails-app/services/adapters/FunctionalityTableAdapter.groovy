package adapters

import FPA_Webapp_G237.Projects
import core.KeyValue

/**
 * Created by developer on 28/05/14.
 */
class FunctionalityTableAdapter {


    public void adapt (List<Projects> projects)
    {
        def res = new HashMap<String,List<KeyValue>> ();
        projects?.each {
            it.id
        }
    }
}
