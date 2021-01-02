/*
 * 
 */
package in.co.rays.model;

import java.util.List;

import in.co.rays.dto.RoleDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;

// TODO: Auto-generated Javadoc
/**
 * Data Access Object of Role.
 * @author Iterator
 * @version 1.0
 */

public interface RoleModelInt {

    /**
     * Add a Role.
     *
     * @param dto the dto
     * @return the long
     * @throws ApplicationException the application exception
     * @throws DuplicateRecordException             : throws when Role already exists
     */
    public long add(RoleDTO dto) throws ApplicationException,
            DuplicateRecordException;

    /**
     * Update a Role.
     *
     * @param dto the dto
     * @throws ApplicationException the application exception
     * @throws DuplicateRecordException             : if updated user record is already exist
     */
    public void update(RoleDTO dto) throws ApplicationException,
            DuplicateRecordException;

    /**
     * Delete a Role.
     *
     * @param dto the dto
     * @throws ApplicationException the application exception
     */
    public void delete(RoleDTO dto) throws ApplicationException;

    /**
     * Find Role by Name.
     *
     * @param name            : get parameter
     * @return dto
     * @throws ApplicationException the application exception
     */
    public RoleDTO findByName(String name) throws ApplicationException;

    /**
     * Find Role by PK.
     *
     * @param pk            : get parameter
     * @return dto
     * @throws ApplicationException the application exception
     */
    public RoleDTO findByPK(long pk) throws ApplicationException;

    /**
     * Search Role with pagination.
     *
     * @param dto            : Search Parameters
     * @param pageNo            : Current Page No.
     * @param pageSize            : Size of Page
     * @return list : List of Role
     * @throws ApplicationException the application exception
     */
    public List search(RoleDTO dto, int pageNo, int pageSize)
            throws ApplicationException;

    /**
     * Search Role.
     *
     * @param dto            : Search Parameters
     * @return list : List of Role
     * @throws ApplicationException the application exception
     */
    public List search(RoleDTO dto) throws ApplicationException;

    /**
     * Gets List of Role.
     *
     * @return list : List of Role
     * @throws ApplicationException the application exception
     */
    public List list() throws ApplicationException;

    /**
     * get List of Role with pagination.
     *
     * @param pageNo            : Current Page No.
     * @param pageSize            : Size of Page
     * @return list : List of Role
     * @throws ApplicationException the application exception
     */
    public List list(int pageNo, int pageSize) throws ApplicationException;

}
