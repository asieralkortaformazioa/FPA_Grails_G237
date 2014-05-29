package FPA_Webapp_G237

class Projects {

	String description

	static hasMany = [functionalities: Functionality]

	static mapping = {
		version false
	}

	static constraints = {
		description nullable: true, maxSize: 50
	}
}
