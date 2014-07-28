package FPA_Webapp_G237
// default package

class Projects {

	String description
	Long productivity

	static hasMany = [adjustmentFactors: AdjustmentFactor,
	                  functionalities: Functionality]

	static mapping = {
		version false
	}

    /* */
	static constraints = {
		description nullable: true , maxSize: 50
	}


    @Override
    public String toString() {
        return "Projects{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", productivity=" + productivity +
                ", version=" + version +
                ", adjustmentFactors=" + adjustmentFactors +
                ", functionalities=" + functionalities +
                '}';
    }
}
