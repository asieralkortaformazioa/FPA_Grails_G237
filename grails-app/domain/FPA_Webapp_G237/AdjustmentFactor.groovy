package FPA_Webapp_G237
// default package

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

class AdjustmentFactor implements Serializable {

	Integer idProject
	Integer idQuestion
	Integer response

	Projects projects

	int hashCode() {
		def builder = new HashCodeBuilder()
		builder.append idProject
		builder.append idQuestion
		builder.toHashCode()
	}

	boolean equals(other) {
		if (other == null) return false
		def builder = new EqualsBuilder()
		builder.append idProject, other.idProject
		builder.append idQuestion, other.idQuestion
		builder.isEquals()
	}

	static belongsTo = [Projects]

	static mapping = {
		id composite: ["idProject", "idQuestion"]
		version false
	}

	static constraints = {
		response nullable: true
	}
}
