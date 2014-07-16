package core

/**
 * Created by developer on 7/16/14.
 */
class Calculations {

    private Float afps;
    private Integer ufps;
    private Integer dataFunctions;
    private Integer transactionalFunctions;
    private Float adjustmentFactor;


    Calculations() {
    }

    Float getAfps() {
        return afps
    }

    void setAfps(Float afps) {
        this.afps = afps
    }

    Integer getUfps() {
        return ufps
    }

    void setUfps(Integer ufps) {
        this.ufps = ufps
    }

    Integer getDataFunctions() {
        return dataFunctions
    }

    void setDataFunctions(Integer dataFunctions) {
        this.dataFunctions = dataFunctions
    }

    Integer getTransactionalFunctions() {
        return transactionalFunctions
    }

    void setTransactionalFunctions(Integer transactionalFunctions) {
        this.transactionalFunctions = transactionalFunctions
    }

    Float getAdjustmentFactor() {
        return adjustmentFactor
    }

    void setAdjustmentFactor(Float adjustmentFactor) {
        this.adjustmentFactor = adjustmentFactor
    }

    @Override
    public String toString() {
        return "Calculations{" +
                "afps=" + afps +
                ", ufps=" + ufps +
                ", dataFunctions=" + dataFunctions +
                ", transactionalFunctions=" + transactionalFunctions +
                ", adjustmentFactor=" + adjustmentFactor +
                '}';
    }
}
