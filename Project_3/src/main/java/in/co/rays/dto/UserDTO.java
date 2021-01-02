package in.co.rays.dto;

import java.sql.Timestamp;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * User DTO class.
 * @author Iterator
 * @version 1.0
 */
public class UserDTO extends BaseDTO{
	
	/** Default Serial Version ID. */
	private static final long serialVersionUID = 1L;
	
	/** Lock Active constant for User. */
	public static final String ACTIVE = "Active";
	
	/** Lock Inactive constant for User. */
	public static final String INACTIVE = "Inactive";
	
	/** First name of user. */
	private String firstName;
	
	/** Last name of user. */
	private String lastName;
	
	/** Login Id of user. */
	private String loginId;
	
	/** password of user. */
	private String password;
	
	/** Confirm Password of User. */
	private String confirmPassword;
	
	/** Date of Birth of user. */
	private Date dob;
	
	/** Mobile Number of user. */
	private String mobileNo;
	
	/** gender of user. */
	private String gender;

	/** Role Id of user. */
	private long roleId;
    
    /** The role name. */
    private String roleName;
	
	/**
	 * Gets the role name.
	 *
	 * @return the role name
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * Sets the role name.
	 *
	 * @param roleName the new role name
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/** Number of unsuccessful login attempt. */
	private int unsuccessfullLogin;
	
	/** Last login Timestamp of user. */
	private Timestamp lastLogin;
	
	/** User Lock. */
	private String userLock = INACTIVE;
	/**
	 * IP Address of User from where User was registred.
	 */
	private String registeredIP;
	
	/** IP Address of User of his last login. */
	private String lastLoginIP;

	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the login id.
	 *
	 * @return the login id
	 */
	public String getLoginId() {
		return loginId;
	}

	/**
	 * Sets the login id.
	 *
	 * @param loginId the new login id
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the dob.
	 *
	 * @return the dob
	 */
	public Date getDob() {
		return dob;
	}

	/**
	 * Sets the dob.
	 *
	 * @param dob the new dob
	 */
	public void setDob(Date dob) {
		this.dob = dob;
	}

	/**
	 * Gets the mobile no.
	 *
	 * @return the mobile no
	 */
	public String getMobileNo() {
		return mobileNo;
	}

	/**
	 * Sets the mobile no.
	 *
	 * @param mobileNo the new mobile no
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	/**
	 * Gets the gender.
	 *
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Sets the gender.
	 *
	 * @param gender the new gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Gets the role id.
	 *
	 * @return the role id
	 */
	public long getRoleId() {
		return roleId;
	}

	/**
	 * Sets the role id.
	 *
	 * @param roleId the new role id
	 */
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	/**
	 * Gets the unsuccessfull login.
	 *
	 * @return the unsuccessfull login
	 */
	public int getUnsuccessfullLogin() {
		return unsuccessfullLogin;
	}

	/**
	 * Sets the unsuccessfull login.
	 *
	 * @param unsuccessfullLogin the new unsuccessfull login
	 */
	public void setUnsuccessfullLogin(int unsuccessfullLogin) {
		this.unsuccessfullLogin = unsuccessfullLogin;
	}

	/**
	 * Gets the last login.
	 *
	 * @return the last login
	 */
	public Timestamp getLastLogin() {
		return lastLogin;
	}

	/**
	 * Sets the last login.
	 *
	 * @param lastLogin the new last login
	 */
	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}

	/**
	 * Gets the user lock.
	 *
	 * @return the user lock
	 */
	public String getUserLock() {
		return userLock;
	}

	/**
	 * Sets the user lock.
	 *
	 * @param userLock the new user lock
	 */
	public void setUserLock(String userLock) {
		this.userLock = userLock;
	}

	/**
	 * Gets the registered IP.
	 *
	 * @return the registered IP
	 */
	public String getRegisteredIP() {
		return registeredIP;
	}

	/**
	 * Sets the registered IP.
	 *
	 * @param registeredIP the new registered IP
	 */
	public void setRegisteredIP(String registeredIP) {
		this.registeredIP = registeredIP;
	}

	/**
	 * Gets the confirm password.
	 *
	 * @return the confirm password
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}

	/**
	 * Sets the confirm password.
	 *
	 * @param confirmPassword the new confirm password
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	/**
	 * Gets the last login IP.
	 *
	 * @return the last login IP
	 */
	public String getLastLoginIP() {
		return lastLoginIP;
	}

	/**
	 * Sets the last login IP.
	 *
	 * @param lastLoginIP the new last login IP
	 */
	public void setLastLoginIP(String lastLoginIP) {
		this.lastLoginIP = lastLoginIP;
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
