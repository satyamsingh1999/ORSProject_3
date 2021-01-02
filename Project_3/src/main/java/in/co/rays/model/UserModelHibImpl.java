/*
 * 
 */
package in.co.rays.model;

import java.util.HashMap;
import java.util.List;

import javax.mail.MessagingException;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.dto.RoleDTO;
import in.co.rays.dto.UserDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.exception.RecordNotFoundException;
import in.co.rays.util.EmailBuilder;
import in.co.rays.util.EmailMessage;
import in.co.rays.util.EmailUtility;
import in.co.rays.util.HibDataSource;

// TODO: Auto-generated Javadoc
/**
 * Hibernate Implementation of UserModel.
 * @author Iterator
 * @version 1.0
 */
public class UserModelHibImpl implements UserModelInt{
	
	/** The log. */
	private static Logger log=Logger.getLogger(UserModelHibImpl.class);
	
	/**
	 * Add a User.
	 *
	 * @param dto the dto
	 * @return the long
	 * @throws ApplicationException the application exception
	 * @throws DuplicateRecordException the duplicate record exception
	 */

	public long add(UserDTO dto) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		long pk=0;
		
		UserDTO dtoExist=findByLogin(dto.getLoginId());
		if (dtoExist != null) {
            throw new DuplicateRecordException("LoginId is already exist");
        }
		Session session=HibDataSource.getSession();
		Transaction transaction=null;
		try {
			RoleDTO rDto=(RoleDTO) session.get(RoleDTO.class, dto.getRoleId());
            dto.setRoleName(rDto.getName());
            transaction = session.beginTransaction();
            session.save(dto);
            pk = dto.getId();
            transaction.commit();
        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            if (transaction != null) {
                transaction.rollback();
            }
//            System.out.println("5");
            throw new ApplicationException("Exception in User Add "
                    + e.getMessage());
        } finally {
            session.close();
        }
        log.debug("Model add End");
        return dto.getId();
	}
	
	/**
	 * Update a User.
	 *
	 * @param dto the dto
	 * @throws ApplicationException the application exception
	 * @throws DuplicateRecordException the duplicate record exception
	 */

	public void update(UserDTO dto) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Session session=null;
		Transaction transaction=null;
		/*UserDTO dtoExist=findByLogin(dto.getLoginId());
		 if (dtoExist != null && dtoExist.getId() != dto.getId()) {
	            throw new DuplicateRecordException("LoginId is already exist");
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
	                throw new ApplicationException("Exception in User Update"
	                        + e.getMessage());
	            }
	        } finally {
	            session.close();
	        }
	        log.debug("Model update End");
	    

	}
	 
 	/**
 	 * Delete a User.
 	 *
 	 * @param dto the dto
 	 * @throws ApplicationException the application exception
 	 */

	public void delete(UserDTO dto) throws ApplicationException {
		log.debug("Model delete Started");
		Session session=null;
		Transaction transaction=null;
		try{
			session=HibDataSource.getSession();
			transaction =session.beginTransaction();
			session.delete(dto);
			transaction.commit();
		}catch(HibernateException e){
			 log.error("Database Exception..", e);
			 if(transaction!=null){
				 transaction.rollback();
			 }
			 throw new ApplicationException("Exception in User Delete"+e.getMessage());
		}finally {
			session.close();
		}
		log.debug("Model delete End");
	}
	 
 	/**
 	 * Find User by PK.
 	 *
 	 * @param login the login
 	 * @return dto
 	 * @throws ApplicationException the application exception
 	 */
	public UserDTO findByLogin(String login) throws ApplicationException {
		 log.debug("Model findByPK Started");
		 Session session=null;
		 UserDTO dto=null;
		 try{
			 session=HibDataSource.getSession();
			 Criteria criteria=session.createCriteria(UserDTO.class);
			 criteria.add(Restrictions.eq("loginId",login));
			 List list=criteria.list();
			 if(list.size()>0){
				 dto=(UserDTO)list.get(0);
			 }
		 }catch(HibernateException e){
			 log.error("Database Exception..", e);
	            throw new ApplicationException("Exception in getting User by Login " + e.getMessage());
		 }finally {
			session.close();
		}
		 log.debug("Model findByLoginId End");
		return dto;
	}
	 
 	/**
 	 * Find User by PK.
 	 *
 	 * @param pk            : get parameter
 	 * @return dto
 	 * @throws ApplicationException the application exception
 	 */

	public UserDTO findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		Session session=null;
		UserDTO dto=null;
		try{
			session=HibDataSource.getSession();
			dto=(UserDTO)session.get(UserDTO.class,pk);
		}catch(HibernateException e){
			log.error("Database Exception..", e);
            throw new ApplicationException("Exception : Exception in getting User by pk");
		}finally {
			session.close();
		}
		return dto;
	}
	
	 /**
 	 * Searches User.
 	 *
 	 * @param dto            : Search Parameters
 	 * @return the list
 	 * @throws ApplicationException the application exception
 	 */
	public List search(UserDTO dto) throws ApplicationException {
		return search(null, 0, 0);
	}
	
	/**
	 * Searches Users with pagination.
	 *
	 * @param dto            : Search Parameters
	 * @param pageNo            : Current Page No.
	 * @param pageSize            : Size of Page
	 * @return list : List of Users
	 * @throws ApplicationException the application exception
	 */

	public List search(UserDTO dto, int pageNo, int pageSize) throws ApplicationException {
		  log.debug("Model search Started");
		  Session session=null;
		  List list=null;
		  try{
			  session=HibDataSource.getSession();
			  Criteria criteria=session.createCriteria(UserDTO.class);
			  if (dto.getId() > 0) {
	                criteria.add(Restrictions.eq("id", dto.getId()));
	            }
	            if (dto.getFirstName() != null && dto.getFirstName().length() > 0) {
	                criteria.add(Restrictions.like("firstName", dto.getFirstName()
	                        + "%"));
	            }
	            if (dto.getLastName() != null && dto.getLastName().length() > 0) {
	                criteria.add(Restrictions.like("lastName", dto.getLastName()
	                        + "%"));
	            }
	            if (dto.getLoginId() != null && dto.getLoginId().length() > 0) {
	                criteria.add(Restrictions.like("loginId", dto.getLoginId() + "%"));
	            }
	            if (dto.getPassword() != null && dto.getPassword().length() > 0) {
	                criteria.add(Restrictions.like("password", dto.getPassword()
	                        + "%"));
	            }
	            if (dto.getGender() != null && dto.getGender().length() > 0) {
	                criteria.add(Restrictions.like("gender", dto.getGender() + "%"));
	            }
	            if (dto.getDob() != null) {
	                criteria.add(Restrictions.eq("dob", dto.getDob()));
	            }
	            if (dto.getLastLogin() != null && dto.getLastLogin().getTime() > 0) {
	                criteria.add(Restrictions.eq("lastLogin", dto.getLastLogin()));
	            }
	            if (dto.getRoleId() > 0) {
	                criteria.add(Restrictions.eq("roleId", dto.getRoleId()));
	            }
	            if (dto.getUnsuccessfullLogin() > 0) {
	                criteria.add(Restrictions.eq("unSuccessfulLogin",
	                        dto.getUnsuccessfullLogin()));
	            }
	            if(pageSize>0){
	            	criteria.setFirstResult((pageNo-1)*pageSize);
	            	criteria.setMaxResults(pageSize);
	            }
	            list=criteria.list();
	            }
	            catch (HibernateException e) {
	                log.error("Database Exception..", e);
	                throw new ApplicationException("Exception in user search");
	            } finally {
	                session.close();
	            }

	            log.debug("Model search End");

		  
		return list;
	}
	 	
	 /**
 	 * Gets List of user.
 	 *
 	 * @return list : List of Users
 	 * @throws ApplicationException the application exception
 	 */

	public List list() throws ApplicationException {

		return list(0, 0);
	}
	
	/**
	 * get List of User with pagination.
	 *
	 * @param pageNo            : Current Page No.
	 * @param pageSize            : Size of Page
	 * @return list : List of Users
	 * @throws ApplicationException the application exception
	 */

	public List list(int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model list Started");
		Session session=null;
		List list=null;
		try{
			session=HibDataSource.getSession();
			Criteria criteria=session.createCriteria(UserDTO.class);
			
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
                    "Exception : Exception in  Users list");
        } finally {
            session.close();
        }

        log.debug("Model list End");
		
		return list;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.model.UserModelInt#changePassword(java.lang.Long, java.lang.String, java.lang.String)
	 */
	public boolean changePassword(Long id, String oldPassword, String newPassword)
			throws RecordNotFoundException, ApplicationException {
		boolean flag = false;
		UserDTO dtoExist = null;

		dtoExist = findByPK(id);
		if (dtoExist != null && dtoExist.getPassword().equals(oldPassword)) {
			dtoExist.setPassword(newPassword);
			try {
				update(dtoExist);
			} catch (Exception e) {

				throw new ApplicationException("LoginId is already exist");
			}
			flag = true;
		} else {
			throw new RecordNotFoundException("Login not exist");
		}

		HashMap<String, String> map = new HashMap<String, String>();

		map.put("loginId", dtoExist.getLoginId());
		map.put("password", dtoExist.getPassword());
		map.put("firstName", dtoExist.getFirstName());
		map.put("lastName", dtoExist.getLastName());

		String message = EmailBuilder.getChangePasswordMessage(map);

		EmailMessage msg = new EmailMessage();

		msg.setTo(dtoExist.getLoginId());
		msg.setSubject("SUNARYS ORS Password has been changed Successfully.");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);

		EmailUtility.sendMail(msg);

		return flag;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.model.UserModelInt#authenticate(java.lang.String, java.lang.String)
	 */
	public UserDTO authenticate(String login, String password) throws ApplicationException {
		log.debug("Model authenticate Started");
        Session session = null;
        UserDTO dto = null;
        session = HibDataSource.getSession();
        Query q = session.createQuery("from UserDTO where loginId=? and password=? ");
        q.setString(0, login);
        q.setString(1, password);
        List list = q.list();
        if (list.size() > 0) {
            dto = (UserDTO) list.get(0);
        } else {
            dto = null;
        }
        log.debug("Model authenticate End");
        return dto;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.model.UserModelInt#lock(java.lang.String)
	 */
	public boolean lock(String login) throws RecordNotFoundException, ApplicationException {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.model.UserModelInt#getRoles(in.co.rays.dto.UserDTO)
	 */
	public List getRoles(UserDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.model.UserModelInt#updateAccess(in.co.rays.dto.UserDTO)
	 */
	public UserDTO updateAccess(UserDTO dto) throws ApplicationException, DuplicateRecordException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.model.UserModelInt#registerUser(in.co.rays.dto.UserDTO)
	 */
	public long registerUser(UserDTO dto) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");

        long pk = add(dto);

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("login", dto.getLoginId());
        map.put("password", dto.getPassword());

        String message = EmailBuilder.getUserRegistrationMessage(map);

        EmailMessage msg = new EmailMessage();

        msg.setTo(dto.getLoginId());
        msg.setSubject("Registration is successful for ORS Project SUNRAYS Technologies");
        msg.setMessage(message);
        msg.setMessageType(EmailMessage.HTML_MSG);

        EmailUtility.sendMail(msg);

        return pk;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.model.UserModelInt#resetPassword(in.co.rays.dto.UserDTO)
	 */
	public boolean resetPassword(UserDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.model.UserModelInt#forgetPassword(java.lang.String)
	 */
	public boolean forgetPassword(String login) throws ApplicationException, RecordNotFoundException {
		 UserDTO userData = findByLogin(login);
	        boolean flag = false;

	        if (userData == null) {
	            throw new RecordNotFoundException("Email Id Does not matched.");

	        }

	        HashMap<String, String> map = new HashMap<String, String>();
	        map.put("login", userData.getLoginId());
	        map.put("password", userData.getPassword());
	        map.put("firstName", userData.getFirstName());
	        map.put("lastName", userData.getLastName());
	        String message = EmailBuilder.getForgetPasswordMessage(map);
	        EmailMessage msg = new EmailMessage();
	        msg.setTo(login);
	        msg.setSubject("SUNARYS ORS Password reset");
	        msg.setMessage(message);
	        msg.setMessageType(EmailMessage.HTML_MSG);
	        EmailUtility.sendMail(msg);
	        flag = true;

	        return flag;
	}

}
