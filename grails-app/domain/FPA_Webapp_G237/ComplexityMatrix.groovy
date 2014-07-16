package FPA_Webapp_G237
// default package

class ComplexityMatrix {

	String type
	Integer horizontalLowLimit
	Integer horizontalTopLimit
	Integer verticalLowLimit
	Integer verticalTopLimit
	Integer complexity

	static mapping = {
		id generator: "assigned"
		version false
	}

	static constraints = {
		type nullable: true, maxSize: 30
		horizontalLowLimit nullable: true
		horizontalTopLimit nullable: true
		verticalLowLimit nullable: true
		verticalTopLimit nullable: true
		complexity nullable: true
	}
}
