/*
 * 
 */
package in.co.rays.model;

import java.util.List;

import javax.mail.MessagingException;

import in.co.rays.dto.UserDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.exception.RecordNotFoundException;

// TODO: Auto-generated Javadoc
/**
 * Data Access Object of Users.
 * @author Iterator
 * @version 1.0
 */
public interface UserModelInt {
	
	/**
	 * Add a user.
	 *
	 * @param dto the dto
	 * @return the long
	 * @throws ApplicationException the application exception
	 * @throws DuplicateRecordException             : throws when user already exists
	 */
	public long add(UserDTO dto) throws ApplicationException,DuplicateRecordException;
	
	 /**
 	 * Update a User.
 	 *
 	 * @param dto the dto
 	 * @throws ApplicationException the application exception
 	 * @throws DuplicateRecordException             : if updated user record is already exist
 	 */
	
	public void  update(UserDTO dto)throws ApplicationException,DuplicateRecordException;
	
	/**
	 * Delete a user.
	 *
	 * @param dto the dto
	 * @throws ApplicationException the application exception
	 */
	
	public void delete(UserDTO dto)throws ApplicationException;
	
	/**
	 * Find user by login.
	 *
	 * @param login            : get parameter
	 * @return dto
	 * @throws ApplicationException the application exception
	 */
	
	public UserDTO findByLogin(String login)throws ApplicationException;
	 
 	/**
 	 * Find user by PK.
 	 *
 	 * @param pk            : get parameter
 	 * @return dto
 	 * @throws ApplicationException the application exception
 	 */
	public UserDTO findByPK(long pk) throws ApplicationException;
	
	/**
	 * Search Users.
	 *
	 * @param dto            : Search Parameters
	 * @return list : List of Users
	 * @throws ApplicationException the application exception
	 */
	public List search(UserDTO dto) throws ApplicationException;
	 
 	/**
 	 * Search Users with pagination.
 	 *
 	 * @param dto            : Search Parameters
 	 * @param pageNo            : Current Page No.
 	 * @param pageSize            : Size of Page
 	 * @return list : List of Users
 	 * @throws ApplicationException the application exception
 	 */
    public List search(UserDTO dto, int pageNo, int pageSize)
            throws ApplicationException;

    /**
     * Get List of Users.
     *
     * @return list : List of Users
     * @throws ApplicationException the application exception
     */
    public List list() throws ApplicationException;

    /**
     * Get List of Users with pagination.
     *
     * @param pageNo            : Current Page No.
     * @param pageSize            : Size of Page
     * @return list : List of Users
     * @throws ApplicationException the application exception
     */
    public List list(int pageNo, int pageSize) throws ApplicationException;

    /**
     * Change Password By pk.
     *
     * @param id the id
     * @param oldPassword the old password
     * @param newPassword the new password
     * @return dto
     * @throws RecordNotFoundException the record not found exception
     * @throws ApplicationException the application exception
     */
    public boolean changePassword(Long id, String oldPassword,String newPassword) throws RecordNotFoundException,
            ApplicationException;

    /**
     * User Authentication.
     *
     * @param login            : User Login
     * @param password            : User Password
     * @return dto : Contains User's information
     * @throws ApplicationException the application exception
     */
    public UserDTO authenticate(String login, String password)
            throws ApplicationException;

    /**
     * Lock User for certain time duration.
     *
     * @param login            : User Login
     * @return boolean : true if success otherwise false
     * @throws RecordNotFoundException             : if user not found
     * @throws ApplicationException the application exception
     */
    public boolean lock(String login) throws RecordNotFoundException,
            ApplicationException;

    /**
     * Get User Roles.
     *
     * @param dto the dto
     * @return List : User Role List
     * @throws ApplicationException the application exception
     */
    public List getRoles(UserDTO dto) throws ApplicationException;

    /**
     * Update User access.
     *
     * @param dto the dto
     * @return dto
     * @throws ApplicationException the application exception
     * @throws DuplicateRecordException the duplicate record exception
     */
    public UserDTO updateAccess(UserDTO dto) throws ApplicationException,
            DuplicateRecordException;

    /**
     * Register a User.
     *
     * @param dto the dto
     * @return the long
     * @throws ApplicationException the application exception
     * @throws DuplicateRecordException the duplicate record exception
     */
    public long registerUser(UserDTO dto) throws ApplicationException,
            DuplicateRecordException;

    /**
     * reset password.
     *
     * @param dto the dto
     * @return true, if successful
     * @throws ApplicationException the application exception
     */
    public boolean resetPassword(UserDTO dto) throws ApplicationException;

    /**
     * forget password.
     *
     * @param login the login
     * @return true, if successful
     * @throws ApplicationException the application exception
     * @throws RecordNotFoundException the record not found exception
     */
    public boolean forgetPassword(String login) throws ApplicationException,
            RecordNotFoundException;

}
