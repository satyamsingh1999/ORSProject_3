package in.co.rays.dto;
// TODO: Auto-generated Javadoc

/**
 * Marksheet DTO class.
 * @author Iterator
 * @version 1.0
 */
public class MarksheetDTO extends BaseDTO {
	
	/** default serial version ID. */
	private static final long serialVersionUID = 1L;
	
	/** Roll number of student. */
	private String rollNo;
	
	/** Name of student. */
	private String name;

	/** Id of student. */
	private long studentId;
	
	/** Student marks of Maths subject. */
	private Integer maths;
	
	/** Student marks of Physics subject. */
	private Integer physics;
	
	/** Student marks of chemistry subject. */
	private Integer chemistry;

	/**
	 * accessors.
	 *
	 * @return the roll no
	 */
	public String getRollNo() {
		return rollNo;
	}

	/**
	 * Sets the roll no.
	 *
	 * @param rollNo the new roll no
	 */
	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the student id.
	 *
	 * @return the student id
	 */
	public long getStudentId() {
		return studentId;
	}

	/**
	 * Sets the student id.
	 *
	 * @param studentId the new student id
	 */
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	/**
	 * Gets the maths.
	 *
	 * @return the maths
	 */
	public Integer getMaths() {
		return maths;
	}

	/**
	 * Sets the maths.
	 *
	 * @param maths the new maths
	 */
	public void setMaths(int maths) {
		this.maths = maths;
	}

	/**
	 * Gets the physics.
	 *
	 * @return the physics
	 */
	public Integer getPhysics() {
		return physics;
	}

	/**
	 * Sets the physics.
	 *
	 * @param physics the new physics
	 */
	public void setPhysics(int physics) {
		this.physics = physics;
	}

	/**
	 * Gets the chemistry.
	 *
	 * @return the chemistry
	 */
	public Integer getChemistry() {
		return chemistry;
	}

	/**
	 * Sets the chemistry.
	 *
	 * @param chemistry the new chemistry
	 */
	public void setChemistry(int chemistry) {
		this.chemistry = chemistry;
	}

	
	/* (non-Javadoc)
	 * @see in.co.rays.dto.DropdownList#getKey()
	 */
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.dto.DropdownList#getValue()
	 */
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.dto.BaseDTO#compareTo(in.co.rays.dto.BaseDTO)
	 */
	public int compareTo(BaseDTO o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
