package FPA_Webapp_G237
// default package

class Functionality {

	String description
	String type
	Integer hcount
	Integer vcount
//	Long projectsId
	Projects projects

	static belongsTo = [Projects]

	static mapping = {
		version false
	}

	static constraints = {
		description nullable: true, maxSize: 50
		type nullable: true, maxSize: 50
		hcount nullable: true
		vcount nullable: true
	}
}
