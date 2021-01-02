package in.co.rays.dto;

import java.io.Serializable;
import java.sql.Timestamp;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseDTO.
 * @author Iterator
 * @version 1.0
 */
public abstract class BaseDTO implements Serializable, DropdownList, Comparable<BaseDTO> {

	/** Non Business primary key. */
	protected long id;

	/** Contains USER ID who created this database record. */
	protected String createdBy;

	/** Contains USER ID who modified this database record. */
	protected String modifiedBy;

	/** Contains Created Timestamp of database record. */
	protected Timestamp createdDateTime;

	/** Contains Modified Timestamp of database record. */
	protected Timestamp modifiedDateTime;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the created by.
	 *
	 * @return the created by
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * Sets the created by.
	 *
	 * @param createdBy the new created by
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * Gets the modified by.
	 *
	 * @return the modified by
	 */
	public String getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * Sets the modified by.
	 *
	 * @param modifiedBy the new modified by
	 */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * Gets the created date time.
	 *
	 * @return the created date time
	 */
	public Timestamp getCreatedDateTime() {
		return createdDateTime;
	}

	/**
	 * Sets the created date time.
	 *
	 * @param createdDatetime the new created date time
	 */
	public void setCreatedDateTime(Timestamp createdDatetime) {
		this.createdDateTime = createdDatetime;
	}

	/**
	 * Gets the modified date time.
	 *
	 * @return the modified date time
	 */
	public Timestamp getModifiedDateTime() {
		return modifiedDateTime;
	}

	/**
	 * Sets the modified date time.
	 *
	 * @param modifiedDatetime the new modified date time
	 */
	public void setModifiedDateTime(Timestamp modifiedDatetime) {
		this.modifiedDateTime = modifiedDatetime;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(BaseDTO next) {
		return getValue().compareTo(next.getValue());
	}
}
