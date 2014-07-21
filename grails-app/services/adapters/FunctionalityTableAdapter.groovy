package adapters

import FPA_Webapp_G237.Functionality

/**
 * Created by developer on 28/05/14.
 */
class FunctionalityTableAdapter {


    public def adapt (List functionalities)
    {
        def res = new ArrayList<String,List<String>> ();
        ((List<Functionality>)functionalities)?.each {
            def props = new ArrayList<String> ();
            props.add(""+it.id)
            props.add(it.description)
            props.add(it.type)
            props.add(""+it.hcount)
            props.add(""+it.vcount)
            //props.add("javascript: deleteFunctionality("+it.id+")")
            res.add(props)
        }
        return res;
    }
}
