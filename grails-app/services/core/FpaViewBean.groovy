package core

/**
 * Created by developer on 27/05/14.
 */
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
    private Integer adjustedFps;

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

    void setAdjustedFps(Integer adjustedFps) {
        this.adjustedFps = adjustedFps
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
                '}';
    }
}
