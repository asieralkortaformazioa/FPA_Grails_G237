package core

import org.grails.datastore.mapping.model.types.Simple

import java.text.SimpleDateFormat

/**
 * Created by developer on 7/16/14.
 */
class Calculations {

    private Float afps;
    private Integer ufps;
    private Integer dataFunctions;
    private Integer transactionalFunctions;
    private Float adjustmentFactor;
    private Double estimateInDays;
    private Double estimateInHours;
    private Date estimatedFinishDate;

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

    Double getEstimateInDays() {
        return estimateInDays
    }

    void setEstimateInDays(Double estimateInDays) {
        this.estimateInDays = estimateInDays
    }

    Double getEstimateInHours() {
        return estimateInHours
    }

    void setEstimateInHours(Double estimateInHours) {
        this.estimateInHours = estimateInHours
    }

    Date getEstimatedFinishDate() {
        return estimatedFinishDate
    }

    void setEstimatedFinishDate(Date estimatedFinishDate) {
        this.estimatedFinishDate = estimatedFinishDate
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd")
        return "Calculations{" +
                "afps=" + afps +
                ", ufps=" + ufps +
                ", dataFunctions=" + dataFunctions +
                ", transactionalFunctions=" + transactionalFunctions +
                ", adjustmentFactor=" + adjustmentFactor +
                ", estimateInDays=" + estimateInDays +
                ", estimateInHours=" + estimateInHours +
                ", estimatedFinishDate=" + sdf.format(estimatedFinishDate) +
                '}';
    }
}
