package core

import FPA_Webapp_G237.Projects
import grails.converters.JSON
import grails.validation.Validateable

/**
 * Created by developer on 27/05/14.
 */
@Validateable
class FpaViewBean {

    private Integer ilfCount
    private Integer eifCount

    private Integer eiCount;
    private Integer eoCount;
    private Integer eqCount;

    private Integer ilfRetCount
    private Integer ilfDetCount

    private Integer eifRetCount
    private Integer eifDetCount

    private Integer eiFtrCount
    private Integer eiDetCount

    private Integer eoFtrCount
    private Integer eoDetCount

    private Integer eqFtrCount
    private Integer eqDetCount

    private Integer unadjustedFps;
    private BigDecimal adjustedFps;


    private Integer af1Count;
    private Integer af2Count;
    private Integer af3Count;
    private Integer af4Count;
    private Integer af5Count;
    private Integer af6Count;
    private Integer af7Count;
    private Integer af8Count;
    private Integer af9Count;
    private Integer af10Count;
    private Integer af11Count;
    private Integer af12Count;
    private Integer af13Count;
    private Integer af14Count;

    private List<Projects> projects;


    FpaViewBean() {
    }

    Integer getEiCount() {
        return eiCount
    }

    void setEiCount(Integer eiCount) {
        this.eiCount = eiCount
    }

    Integer getEoCount() {
        return eoCount
    }

    void setEoCount(Integer eoCount) {
        this.eoCount = eoCount
    }

    Integer getEqCount() {
        return eqCount
    }

    void setEqCount(Integer eqCount) {
        this.eqCount = eqCount
    }

    Integer getIlfCount() {
        return ilfCount
    }

    void setIlfCount(Integer ilfCount) {
        this.ilfCount = ilfCount
    }

    Integer getEifCount() {
        return eifCount
    }

    void setEifCount(Integer eifCount) {
        this.eifCount = eifCount
    }

    Integer getIlfRetCount() {
        return ilfRetCount
    }

    void setIlfRetCount(Integer ilfRetCount) {
        this.ilfRetCount = ilfRetCount
    }

    Integer getIlfDetCount() {
        return ilfDetCount
    }

    void setIlfDetCount(Integer ilfDetCount) {
        this.ilfDetCount = ilfDetCount
    }

    Integer getEifRetCount() {
        return eifRetCount
    }

    void setEifRetCount(Integer eifRetCount) {
        this.eifRetCount = eifRetCount
    }

    Integer getEifDetCount() {
        return eifDetCount
    }

    void setEifDetCount(Integer eifDetCount) {
        this.eifDetCount = eifDetCount
    }

    Integer getEiFtrCount() {
        return eiFtrCount
    }

    void setEiFtrCount(Integer eiFtrCount) {
        this.eiFtrCount = eiFtrCount
    }

    Integer getEiDetCount() {
        return eiDetCount
    }

    void setEiDetCount(Integer eiDetCount) {
        this.eiDetCount = eiDetCount
    }

    Integer getEoFtrCount() {
        return eoFtrCount
    }

    void setEoFtrCount(Integer eoFtrCount) {
        this.eoFtrCount = eoFtrCount
    }

    Integer getEoDetCount() {
        return eoDetCount
    }

    void setEoDetCount(Integer eoDetCount) {
        this.eoDetCount = eoDetCount
    }

    Integer getEqFtrCount() {
        return eqFtrCount
    }

    void setEqFtrCount(Integer eqFtrCount) {
        this.eqFtrCount = eqFtrCount
    }

    Integer getEqDetCount() {
        return eqDetCount
    }

    void setEqDetCount(Integer eqDetCount) {
        this.eqDetCount = eqDetCount
    }

    Integer getUnadjustedFps() {
        return unadjustedFps
    }

    void setUnadjustedFps(Integer unadjustedFps) {
        this.unadjustedFps = unadjustedFps
    }

    Integer getAdjustedFps() {
        return adjustedFps
    }

    void setAdjustedFps(BigDecimal adjustedFps) {
        this.adjustedFps = adjustedFps
    }

    Integer getAf1Count() {
        return af1Count
    }

    void setAf1Count(Integer af1Count) {
        this.af1Count = af1Count
    }

    Integer getAf2Count() {
        return af2Count
    }

    void setAf2Count(Integer af2Count) {
        this.af2Count = af2Count
    }

    Integer getAf3Count() {
        return af3Count
    }

    void setAf3Count(Integer af3Count) {
        this.af3Count = af3Count
    }

    Integer getAf4Count() {
        return af4Count
    }

    void setAf4Count(Integer af4Count) {
        this.af4Count = af4Count
    }

    Integer getAf5Count() {
        return af5Count
    }

    void setAf5Count(Integer af5Count) {
        this.af5Count = af5Count
    }

    Integer getAf6Count() {
        return af6Count
    }

    void setAf6Count(Integer af6Count) {
        this.af6Count = af6Count
    }

    Integer getAf7Count() {
        return af7Count
    }

    void setAf7Count(Integer af7Count) {
        this.af7Count = af7Count
    }

    Integer getAf8Count() {
        return af8Count
    }

    void setAf8Count(Integer af8Count) {
        this.af8Count = af8Count
    }

    Integer getAf9Count() {
        return af9Count
    }

    void setAf9Count(Integer af9Count) {
        this.af9Count = af9Count
    }

    Integer getAf10Count() {
        return af10Count
    }

    void setAf10Count(Integer af10Count) {
        this.af10Count = af10Count
    }

    Integer getAf11Count() {
        return af11Count
    }

    void setAf11Count(Integer af11Count) {
        this.af11Count = af11Count
    }

    Integer getAf12Count() {
        return af12Count
    }

    void setAf12Count(Integer af12Count) {
        this.af12Count = af12Count
    }

    Integer getAf13Count() {
        return af13Count
    }

    void setAf13Count(Integer af13Count) {
        this.af13Count = af13Count
    }

    Integer getAf14Count() {
        return af14Count
    }

    void setAf14Count(Integer af14Count) {
        this.af14Count = af14Count
    }

    List<Projects> getProjects() {
        return projects
    }


    String getJsonProjects ()
    {
        def converter = new JSON(target: getProjects());
        def str = converter.toString();
        println ("Converer:"+str);
        return str

    }

    void setProjects(List<Projects> projects) {
        this.projects = projects
    }


    List<String> getFunctions ()
    {
        return FunctionTypes.getFucntions();
    }

    @Override
    public String toString() {
        return "FpaViewBean{" +
                "ilfCount=" + ilfCount +
                ", eifCount=" + eifCount +
                ", eiCount=" + eiCount +
                ", eoCount=" + eoCount +
                ", eqCount=" + eqCount +
                ", ilfRetCount=" + ilfRetCount +
                ", ilfDetCount=" + ilfDetCount +
                ", eifRetCount=" + eifRetCount +
                ", eifDetCount=" + eifDetCount +
                ", eiFtrCount=" + eiFtrCount +
                ", eiDetCount=" + eiDetCount +
                ", eoFtrCount=" + eoFtrCount +
                ", eoDetCount=" + eoDetCount +
                ", eqFtrCount=" + eqFtrCount +
                ", eqDetCount=" + eqDetCount +
                ", unadjustedFps=" + unadjustedFps +
                ", adjustedFps=" + adjustedFps +
                ", af1Count=" + af1Count +
                ", af2Count=" + af2Count +
                ", af3Count=" + af3Count +
                ", af4Count=" + af4Count +
                ", af5Count=" + af5Count +
                ", af6Count=" + af6Count +
                ", af7Count=" + af7Count +
                ", af8Count=" + af8Count +
                ", af9Count=" + af9Count +
                ", af10Count=" + af10Count +
                ", af11Count=" + af11Count +
                ", af12Count=" + af12Count +
                ", af13Count=" + af13Count +
                ", af14Count=" + af14Count +
                '}';
    }
}
