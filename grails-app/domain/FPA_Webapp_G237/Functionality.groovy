package FPA_Webapp_G237
// default package

class Functionality {

	String description
	String type
	Integer hcount
	Integer vcount

	String section
	Projects projects

	static belongsTo = [Projects]

	static mapping = {
		version false
	}

    /* */
	static constraints = {
		description nullable:  true , maxSize: 150
		type nullable: true , maxSize: 50
		hcount nullable: true
		vcount nullable: true
		section  nullable: true , maxSize: 100
	}


    @Override
    public String toString() {
        return "Functionality{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", hcount=" + hcount +
                ", vcount=" + vcount +
                ", section='" + section + '\'' +
//                ", projects=" + projects +
                ", version=" + version +
                '}';
    }
}
