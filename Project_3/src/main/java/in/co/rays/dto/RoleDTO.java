package in.co.rays.dto;

// TODO: Auto-generated Javadoc
/**
 * Role DTO class.
 * @author Iterator
 * @version 1.0
 */
public class RoleDTO extends BaseDTO{
	
	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant STUDENT. */
	public static final long STUDENT = 2;
	
	/** The Constant ADMIN. */
	public static final long ADMIN=1;
	
	/** The Constant COLLEGE. */
	public static final long COLLEGE=3; 
	
	/** The Constant FACULTY. */
	public static final long FACULTY=4; 
	
	/** Role name. */
	private String name;
	
	/** Role description. */
	private String description;

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
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	
	/* (non-Javadoc)
	 * @see in.co.rays.dto.DropdownList#getKey()
	 */
	public String getKey() {
		return id+"";
	}

	/* (non-Javadoc)
	 * @see in.co.rays.dto.DropdownList#getValue()
	 */
	public String getValue() {
	
		return name;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.dto.BaseDTO#compareTo(in.co.rays.dto.BaseDTO)
	 */
	public int compareTo(BaseDTO o) {
		return 0;
	}
}
