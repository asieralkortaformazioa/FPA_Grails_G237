package FPA_Webapp_G237

class Complexity {

	String description

	static mapping = {
		id generator: "assigned"
		version false
	}

	static constraints = {
		description nullable: true, maxSize: 50
	}
}
