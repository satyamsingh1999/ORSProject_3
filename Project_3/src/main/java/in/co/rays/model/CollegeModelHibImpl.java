/*
 * 
 */
package in.co.rays.model;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.dto.CollegeDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.util.HibDataSource;

// TODO: Auto-generated Javadoc
/**
 * Hibernate Implementation of CollegeModel.
 * @author Iterator
 * @version 1.0
 */
public class CollegeModelHibImpl implements  CollegeModelInt {

    /** The log. */
    private static Logger log = Logger.getLogger(CollegeModelHibImpl.class);

    /**
     * Add a College.
     *
     * @param dto the dto
     * @return the long
     * @throws ApplicationException the application exception
     * @throws DuplicateRecordException the duplicate record exception
     */
    public long add(CollegeDTO dto) throws ApplicationException, DuplicateRecordException {
        log.debug("Model add Started");
        long pk = 0;
        CollegeDTO duplicateCollegeName = findByName(dto.getName());

        if (duplicateCollegeName != null) {
            throw new DuplicateRecordException("College Name already exists");
        }

        Session session = null;
        Transaction transaction = null;
        try {
        	session=HibDataSource.getSession();
            transaction = session.beginTransaction();
            session.save(dto);
            pk = dto.getId();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            log.error("Database Exception..", e);
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ApplicationException("Exception in College Add "
                    + e.getMessage());
        } finally {
            session.close();
        }

        log.debug("Model add End");
        return dto.getId();
    }

    /**
     * Delete a College.
     *
     * @param dto the dto
     * @throws ApplicationException the application exception
     */
    public void delete(CollegeDTO dto) throws ApplicationException {
        log.debug("Model delete Started");
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibDataSource.getSession();
            transaction = session.beginTransaction();
            session.delete(dto);
            transaction.commit();
        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ApplicationException("Exception in college Delete"
                    + e.getMessage());
        } finally {
            session.close();
        }
        log.debug("Model delete End");
    }

    /**
     * Find User by College Name.
     *
     * @param name the name
     * @return dto
     * @throws ApplicationException the application exception
     */
    public CollegeDTO findByName(String name) throws ApplicationException {
        log.debug("Model findByName Started");
        Session session = null;
        CollegeDTO dto = null;
        try {
            session = HibDataSource.getSession();
            Criteria criteria = session.createCriteria(CollegeDTO.class);
            criteria.add(Restrictions.eq("name", name));
            List list = criteria.list();
            if (list.size() > 0) {
                dto = (CollegeDTO) list.get(0);
            }

        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception in getting User by Login " + e.getMessage());

        } finally {
            session.close();
        }

        log.debug("Model findByName End");
        return dto;
    }

    /**
     * Find Collage by PK.
     *
     * @param pk            : get parameter
     * @return dto
     * @throws ApplicationException the application exception
     */
    public CollegeDTO findByPK(long pk) throws ApplicationException {
        log.debug("Model findByPK Started");
        Session session = null;
        CollegeDTO dto = null;
        try {
            session = HibDataSource.getSession();
            dto = (CollegeDTO) session.get(CollegeDTO.class, pk);
        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting College by pk");
        } finally {
            session.close();
        }

        log.debug("Model findByPK End");
        return dto;
    }

    /**
     * Update a Collage.
     *
     * @param dto the dto
     * @throws ApplicationException the application exception
     * @throws DuplicateRecordException the duplicate record exception
     */
    public void update(CollegeDTO dto) throws ApplicationException,
            DuplicateRecordException {
        log.debug("Model update Started");
        Session session = null;
        Transaction transaction = null;

      /*  CollegeDTO dtoExist = findByName(dto.getName());

        // Check if updated College already exist
        if (dtoExist != null && dtoExist.getId() != dto.getId()) {
            throw new DuplicateRecordException("College is already exist");
        }*/

        try {

            session = HibDataSource.getSession();
            transaction = session.beginTransaction();
            session.update(dto);
            transaction.commit();
        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            if (transaction != null) {
                transaction.rollback();
                throw new ApplicationException("Exception in College Update"
                        + e.getMessage());
            }
        } finally {
            session.close();
        }
        log.debug("Model update End");
    }

    /**
     * Searches Colleges with pagination.
     *
     * @param dto            : Search Parameters
     * @param pageNo            : Current Page No.
     * @param pageSize            : Size of Page
     * @return list : List of Colleges
     * @throws ApplicationException the application exception
     */
    public List search(CollegeDTO dto, int pageNo, int pageSize)
            throws ApplicationException {
        log.debug("Model search Started");
        Session session = null;
        List list = null;
        try {
            session = HibDataSource.getSession();
            Criteria criteria = session.createCriteria(CollegeDTO.class);

            if (dto.getId() > 0) {
                criteria.add(Restrictions.eq("id", dto.getId()));
            }
            if (dto.getName() != null && dto.getName().length() > 0) {
                criteria.add(Restrictions.like("name", dto.getName() + "%"));
            }
            if (dto.getAddress() != null && dto.getAddress().length() > 0) {
                criteria.add(Restrictions.like("address", dto.getAddress()
                        + "%"));
            }
            if (dto.getState() != null && dto.getState().length() > 0) {
                criteria.add(Restrictions.like("state", dto.getState() + "%"));
            }
            if (dto.getCity() != null && dto.getCity().length() > 0) {
                criteria.add(Restrictions.like("city", dto.getCity() + "%"));
            }

            // if page size is greater than zero the apply pagination
            if (pageSize > 0) {
                criteria.setFirstResult(((pageNo - 1) * pageSize));
                criteria.setMaxResults(pageSize);
            }

            list = criteria.list();
        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            throw new ApplicationException("Exception in college search");
        } finally {
            session.close();
        }

        log.debug("Model search End");
        return list;
    }

    /**
     * Search College.
     *
     * @param dto            : Search Parameters
     * @return the list
     * @throws ApplicationException the application exception
     */
    public List search(CollegeDTO dto) throws ApplicationException {
        return search(dto, 0, 0);
    }

    /**
     * Gets List of College.
     *
     * @return list : List of College
     * @throws ApplicationException the application exception
     */
    public List list() throws ApplicationException {
        return list(0, 0);
    }

    /**
     * get List of College with pagination.
     *
     * @param pageNo            : Current Page No.
     * @param pageSize            : Size of Page
     * @return list : List of College
     * @throws ApplicationException the application exception
     */
    public List list(int pageNo, int pageSize) throws ApplicationException {
        log.debug("Model list Started");
        Session session = null;
        List list = null;
        try {
            session = HibDataSource.getSession();
            Criteria criteria = session.createCriteria(CollegeDTO.class);

            // if page size is greater than zero then apply pagination
            if (pageSize > 0) {
                pageNo = ((pageNo - 1) * pageSize) + 1;
                criteria.setFirstResult(pageNo);
                criteria.setMaxResults(pageSize);
            }

            list = criteria.list();
        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in  College list");
        } finally {
            session.close();
        }

        log.debug("Model list End");
        return list;
    }

}

