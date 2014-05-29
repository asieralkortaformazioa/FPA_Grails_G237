package FPA_Webapp_G237

class CplxTranslation {

	String type
	Integer complexity
	Integer ufp

	static mapping = {
		id generator: "assigned"
		version false
	}

	static constraints = {
		type nullable: true, maxSize: 30
		complexity nullable: true
		ufp nullable: true
	}
}
