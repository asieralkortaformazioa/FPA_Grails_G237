package core

/**
 * Created by developer on 28/05/14.
 */
enum FunctionTypes {

    ILF, EIF, EI, EO, EQ


    public static List<String> getFucntions ()
    {
        def list = new ArrayList<String> ();
        list.add(ILF.name())
        list.add(EIF.name())
        list.add(EI.name())
        list.add(EO.name())
        list.add(EQ.name())

        return list;
    }
}
