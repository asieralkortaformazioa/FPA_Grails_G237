package FPA_Webapp_G237
// default package

class Projects {

	String description

	static hasMany = [adjustmentFactors: AdjustmentFactor,
	                  functionalities: Functionality]

	static mapping = {
		version false
	}

	static constraints = {
		description nullable: true, maxSize: 50
	}
}
