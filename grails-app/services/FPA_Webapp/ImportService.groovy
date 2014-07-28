package FPA_Webapp

import FPA_Webapp_G237.Functionality
import FPA_Webapp_G237.Projects
import org.apache.poi.hssf.usermodel.HSSFCell
import org.apache.poi.hssf.usermodel.HSSFRow
import org.apache.poi.hssf.usermodel.HSSFSheet
import org.apache.poi.hssf.usermodel.HSSFWorkbook

/**
 * Created by developer on 7/25/14.
 */
class ImportService {

    def projectsService = new ProjectService();

    private Integer cellIndexProjectName = 1;
    private Integer cellIndexProjectProductivity = 4;
    private Integer rowIndexProjectName = 1;

    private Integer cellIndexDesc = 2;

//    EIF
    private Integer cellIndexEifHCount = 6;
    private Integer cellIndexEifVCount = 7;

//    EOF
    private Integer cellIndexEofHCount = 10;
    private Integer cellIndexEofVCount = 11;
//    ILF
    private Integer cellIndexIlfHCount = 13;
    private Integer cellIndexIlfVCount = 14;


//EI - OK
    private Integer cellIndexEiHCount = 18;
    private Integer cellIndexEiVCount = 19;
//EO - OK
    private Integer cellIndexEoHCount = 22;
    private Integer cellIndexEoVCount = 23;
//EQ - EQ
    private Integer cellIndexEqHCount = 26;
    private Integer cellIndexEqVCount = 27;


    def importFromExcel(String file) {

//         String fileName ="/mnt/shared/Caf/Projects/Calidad/Dokumentazioa/FPA_FP_Count_Invertido-v01.xls";
        InputStream myxls = new FileInputStream(file);
        HSSFWorkbook wb = new HSSFWorkbook(myxls);
        HSSFSheet sheet = wb.getSheet("ScreenCount")




        HSSFRow rowProj = sheet.getRow(rowIndexProjectName)
        String projectName = rowProj.getCell(cellIndexProjectName)

        String productivity = rowProj.getCell(cellIndexProjectProductivity)


        Projects project = new Projects(description: projectName, productivity: productivity?.toFloat())

        Projects savedProj =  projectsService.saveProject(project)

        int rowCount = 18;

        Set<Functionality> functs = new HashSet<Functionality>()
        boolean end = false;
        while (!end) {
            HSSFRow row = sheet.getRow(rowCount)

//            println "Processing row:"+rowCount
            HSSFCell cell = row.getCell(cellIndexDesc)
            String desc = cell.getStringCellValue();

            if (desc != null && "END".equals(desc.trim()))
                end = true;
            else {
                def funct = getFunctionality(row, project);
                if (funct != null && funct.getDescription() != null && !"".equals(funct.getDescription()) && funct.getType() != null && !"".equals(funct.getType())) {
//                   functs.add(funct)
                    savedProj.addToFunctionalities(funct)
                }
            }
            rowCount++;
        }
//        savedProj?.getFunctionalities()?.addAll(functs)


//         savedProj.setFunctionalities(functs)

//
       Projects result =  projectsService.saveProject(savedProj);
       return (result!=null)
    }


    private Functionality getFunctionality(HSSFRow row, Projects proj) {
        int cellIndexSection = 1
        int cellIndexDesc = 2


        String section = row.getCell(cellIndexSection).getStringCellValue()
        HSSFCell cell = row.getCell(cellIndexDesc)
        String desc = cell.getStringCellValue()
        println("Importing  " + section + ": " + desc);
        Functionality funct = new Functionality(section: section, description: desc);
//        , projects: proj

        //Get EIF
        try {
            Double eifH = row.getCell(cellIndexEifHCount).getNumericCellValue()
            Double eifV = row.getCell(cellIndexEifVCount).getNumericCellValue()
            if (eifH != null && !"".equals(eifH) && eifV != null && !"".equals(eifV) && eifH != 0.0 && eifV != 0.0) {
                funct.setHcount(eifH?.toInteger());
                funct.setVcount(eifV?.toInteger());
                funct.setType(CountService.ComplexityTypes.EIF.name())
            }
        } catch (Exception e) {
            println "Processing EIF"
            e.printStackTrace();
        }

        //Get Eof
        try {
            Double eofH = row.getCell(cellIndexEofHCount).getNumericCellValue()
            Double eofV = row.getCell(cellIndexEofVCount).getNumericCellValue()
            if (eofH != null && !"".equals(eofH) && eofV != null && !"".equals(eofV) && eofH != 0.0 && eofV != 0.0) {
                funct.setHcount(eofH?.toInteger());
                funct.setVcount(eofV?.toInteger());
                funct.setType(CountService.ComplexityTypes.EOF.name())
            }
        } catch (Exception e) {
            println "Processing EOF"
            e.printStackTrace();
        }

        //Get Ilf
        try {
            Double ilfH = row.getCell(cellIndexIlfHCount).getNumericCellValue()
            Double ilfV = row.getCell(cellIndexIlfVCount).getNumericCellValue()
            if (ilfH != null && !"".equals(ilfH) && ilfV != null && !"".equals(ilfV) && ilfH != 0.0 && ilfV != 0.0) {
                funct.setHcount(ilfH?.toInteger());
                funct.setVcount(ilfV?.toInteger());
                funct.setType(CountService.ComplexityTypes.ILF.name())
            }
        } catch (Exception e) {
            println "Processing ILF"
            e.printStackTrace();
        }

        //Get EI
        try {
            Double eiH = row.getCell(cellIndexEiHCount).getNumericCellValue()
            Double eiV = row.getCell(cellIndexEiVCount).getNumericCellValue()
            if (eiH != null && !"".equals(eiH) && eiV != null && !"".equals(eiV) && eiH != 0.0 && eiV != 0.0) {
                funct.setHcount(eiH?.toInteger());
                funct.setVcount(eiV?.toInteger());
                funct.setType(CountService.ComplexityTypes.EI.name())
            }
        } catch (Exception e) {
            println "Processing EI"
            e.printStackTrace();
        }

        //Get EO
        try {
            Double eoH = row.getCell(cellIndexEoHCount).getNumericCellValue()
            Double eoV = row.getCell(cellIndexEoVCount).getNumericCellValue()
            if (eoH != null && !"".equals(eoH) && eoV != null && !"".equals(eoV) && eoH != 0.0 && eoV != 0.0) {
                funct.setHcount(eoH?.toInteger());
                funct.setVcount(eoV?.toInteger());
                funct.setType(CountService.ComplexityTypes.EO.name())
            }
        } catch (Exception e) {
            println "Processing EO"
            e.printStackTrace();
        }

        //Get EQ
        try {
            Double eqH = row.getCell(cellIndexEqHCount).getNumericCellValue()
            Double eqV = row.getCell(cellIndexEqVCount).getNumericCellValue()
            if (eqH != null && !"".equals(eqH) && eqV != null && !"".equals(eqV) && eqH != 0.0 && eqV != 0.0) {
                funct.setHcount(eqH?.toInteger());
                funct.setVcount(eqV?.toInteger());
                funct.setType(CountService.ComplexityTypes.EQ.name())
            }
        } catch (Exception e) {
            println "Processing EQ"
            e.printStackTrace();
        }

        println("Functionality: "+funct);
        return funct;
    }
}
