/*
 * 
 */
package in.co.rays.model;

import java.util.List;

import in.co.rays.dto.TimeTableDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;

// TODO: Auto-generated Javadoc
/**
 * The Interface TimeTableModelInt.
 * @author Iterator
 * @version 1.0
 */
public interface TimeTableModelInt {
	
	
	/**
	 * Check by course name.
	 *
	 * @param CourseId the course id
	 * @param ExamDate the exam date
	 * @return the time table DTO
	 * @throws ApplicationException the application exception
	 */
	public TimeTableDTO  checkByCourseName(long CourseId,java.util.Date ExamDate)
		    throws ApplicationException;
	
	
	
	
	/**
	 * Check by subject name.
	 *
	 * @param CourseId the course id
	 * @param SubjectId the subject id
	 * @param ExamDate the exam date
	 * @return the time table DTO
	 * @throws ApplicationException the application exception
	 */
	public TimeTableDTO  checkBySubjectName(long CourseId,long SubjectId,java.util.Date ExamDate)
		    throws ApplicationException;
	
	
	
	/**
	 * Check bysemester.
	 *
	 * @param CourseId the course id
	 * @param SubjectId the subject id
	 * @param semester the semester
	 * @param ExamDate the exam date
	 * @return the time table DTO
	 * @throws ApplicationException the application exception
	 */
	public TimeTableDTO checkBysemester(long CourseId,long SubjectId,String semester,java.util.Date ExamDate)
		    throws ApplicationException;
	
	
	
	  /**
  	 * Add a Student.
  	 *
  	 * @param dto the dto
  	 * @return the long
  	 * @throws ApplicationException the application exception
  	 * @throws DuplicateRecordException             : throws when Student already exists
  	 */
    public long add(TimeTableDTO dto) throws ApplicationException,
            DuplicateRecordException
            ;

    /**
     * Update a Student.
     *
     * @param dto the dto
     * @throws ApplicationException the application exception
     * @throws DuplicateRecordException             : if updated user record is already exist
     */
    public void update(TimeTableDTO dto) throws ApplicationException,
            DuplicateRecordException;

    /**
     * Delete a Student.
     *
     * @param dto the dto
     * @throws ApplicationException the application exception
     */
    public void delete(TimeTableDTO dto) throws ApplicationException;

    /**
     * Find Student by EmailId.
     *
     * @param emailId the email id
     * @return dto
     * @throws ApplicationException the application exception
     */
    public TimeTableDTO name(String emailId) throws ApplicationException;

    /**
     * Find Student by PK.
     *
     * @param pk            : get parameter
     * @return dto
     * @throws ApplicationException the application exception
     */
    public TimeTableDTO findByPK(long pk) throws ApplicationException;

    /**
     * Search Student with pagination.
     *
     * @param dto the dto
     * @param pageNo the page no
     * @param pageSize : Size of Page
     * @return list : List of Student
     * @throws ApplicationException the application exception
     */
    public List search(TimeTableDTO dto, int pageNo, int pageSize)
            throws ApplicationException;

    /**
     * Search Student.
     *
     * @param dto the dto
     * @return list : List of Student
     * @throws ApplicationException the application exception
     */
    public List search(TimeTableDTO dto) throws ApplicationException;

    /**
     * Gets List of College.
     *
     * @return list : List of College
     * @throws ApplicationException the application exception
     */
    public List list() throws ApplicationException;

    /**
     * get List of College with pagination.
     *
     * @param pageNo : Current Page No.
     * @param pageSize the page size
     * @return list : List of College
     * @throws ApplicationException the application exception
     */
    public List list(int pageNo, int pageSize) throws ApplicationException;

}

