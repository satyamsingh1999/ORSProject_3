/*
 * 
 */
package in.co.rays.model;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.dto.CourseDTO;
import in.co.rays.dto.SubjectDTO;
import in.co.rays.dto.TimeTableDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.util.HibDataSource;

// TODO: Auto-generated Javadoc
/**
 * The Class TimeTableModelHibImpl.
 * 
 * @author Iterator
 * @version 1.0
 */
public class TimeTableModelHibImpl implements TimeTableModelInt {

	/** The log. */
	private static Logger log = Logger.getLogger(MarksheetModelHibImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.co.rays.model.TimeTableModelInt#checkByCourseName(long,
	 * java.util.Date)
	 */
	public TimeTableDTO checkByCourseName(long CourseId, Date ExamDate) throws ApplicationException {

		Session session = null;
		TimeTableDTO dto = null;
		try {
			session = HibDataSource.getSession();

			Criteria criteria = session.createCriteria(TimeTableDTO.class);

			criteria.add(Restrictions.eq("courseId", CourseId));
			criteria.add(Restrictions.like("examDate", ExamDate));

			List list = criteria.list();
			if (list.size() == 1) {

				dto = (TimeTableDTO) list.get(0);
			}

		} catch (Exception e) {
			log.error(e);
			throw new ApplicationException("Exception in  marksheet list" + e.getMessage());
		} finally {
			session.close();
		}

		log.debug("Model getMeritList End");
		return dto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.co.rays.model.TimeTableModelInt#checkBySubjectName(long, long,
	 * java.util.Date)
	 */
	public TimeTableDTO checkBySubjectName(long CourseId, long SubjectId, Date ExamDate) throws ApplicationException {
		Session session = null;
		TimeTableDTO dto = null;
		try {
			session = HibDataSource.getSession();

			Criteria criteria = session.createCriteria(TimeTableDTO.class);

			// if page size is greater than zero then apply pagination
			criteria.add(Restrictions.eq("courseId", CourseId));
			criteria.add(Restrictions.eq("subjectId", SubjectId));

			criteria.add(Restrictions.like("examDate", ExamDate));

			List list = criteria.list();
			if (list.size() == 1) {

				dto = (TimeTableDTO) list.get(0);
			}

		} catch (Exception e) {
			log.error(e);
			throw new ApplicationException("Exception in  marksheet list" + e.getMessage());
		} finally {
			session.close();
		}

		log.debug("Model getMeritList End");
		return dto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.co.rays.model.TimeTableModelInt#checkBysemester(long, long,
	 * java.lang.String, java.util.Date)
	 */
	public TimeTableDTO checkBysemester(long CourseId, long SubjectId, String semester, Date ExamDate)
			throws ApplicationException {
		Session session = null;
		TimeTableDTO dto = null;
		try {
			session = HibDataSource.getSession();

			Criteria criteria = session.createCriteria(TimeTableDTO.class);

			// if page size is greater than zero then apply pagination
			criteria.add(Restrictions.eq("courseId", CourseId));
			criteria.add(Restrictions.eq("subjectId", SubjectId));
			criteria.add(Restrictions.like("semester", semester));

			criteria.add(Restrictions.like("examDate", ExamDate));

			List list = criteria.list();
			if (list.size() == 1) {

				dto = (TimeTableDTO) list.get(0);
			}

		} catch (Exception e) {
			log.error(e);
			throw new ApplicationException("Exception in  marksheet list" + e.getMessage());
		} finally {
			session.close();
		}

		log.debug("Model getMeritList End");
		return dto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.co.rays.model.TimeTableModelInt#add(in.co.rays.dto.TimeTableDTO)
	 */
	public long add(TimeTableDTO dto) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		long pk = 0;

		SubjectModelInt subjectModel = ModelFactory.getInstance().getSubjectModel();
		CourseModelInt courseModel = ModelFactory.getInstance().getCourseModel();

		SubjectDTO subjectdto = subjectModel.findByPK(dto.getSubjectId());
		CourseDTO coursedto = courseModel.findByPK(dto.getCourseId());

		dto.setSubjectName(subjectdto.getSubjectName());
		dto.setCourseName(coursedto.getCourseName());

		Session session = HibDataSource.getSession();
		Transaction transaction = null;
		try {
			TimeTableDTO duplicatename = checkByCourseName(dto.getCourseId(), dto.getExamDate());

			TimeTableDTO duplicatename1 = checkBysemester(dto.getCourseId(), dto.getSubjectId(), dto.getSemester(),
					dto.getExamDate());

			TimeTableDTO duplicatename2 = checkBySubjectName(dto.getCourseId(), dto.getSubjectId(), dto.getExamDate());
			if (duplicatename1 != null || duplicatename != null || duplicatename2 != null) {
				throw new DuplicateRecordException("Time Table already exist");

			}
			transaction = session.beginTransaction();
			session.save(dto);
			pk = dto.getId();
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Database Exception..", e);
			if (transaction != null) {
				transaction.rollback();
			}
			// System.out.println("5");
			throw new ApplicationException("Exception in TimeTable Add " + e.getMessage());
		} finally {
			session.close();
		}
		log.debug("Model add End");
		return dto.getId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * in.co.rays.model.TimeTableModelInt#update(in.co.rays.dto.TimeTableDTO)
	 */
	public void update(TimeTableDTO dto) throws ApplicationException, DuplicateRecordException {

		SubjectModelInt subjectModel = ModelFactory.getInstance().getSubjectModel();

		SubjectDTO subjectdto = subjectModel.findByPK(dto.getSubjectId());

		dto.setSubjectName(subjectdto.getSubjectName());

		CourseModelInt courseModel = ModelFactory.getInstance().getCourseModel();

		CourseDTO coursedto = courseModel.findByPK(dto.getCourseId());

		dto.setCourseName(coursedto.getCourseName());

		Session session = HibDataSource.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(dto);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Database Exception..", e);
			if (transaction != null) {
				transaction.rollback();
			}
			// System.out.println("5");
			throw new ApplicationException("Exception in TimeTable Add " + e.getMessage());
		} finally {
			session.close();
		}
		log.debug("Model add End");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * in.co.rays.model.TimeTableModelInt#delete(in.co.rays.dto.TimeTableDTO)
	 */
	public void delete(TimeTableDTO dto) throws ApplicationException {
		Session session = HibDataSource.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.delete(dto);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Database Exception..", e);
			if (transaction != null) {
				transaction.rollback();
			}
//			System.out.println("5");
			throw new ApplicationException("Exception in TimeTable Add " + e.getMessage());
		} finally {
			session.close();
		}
		log.debug("Model add End");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.co.rays.model.TimeTableModelInt#name(java.lang.String)
	 */
	public TimeTableDTO name(String emailId) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.co.rays.model.TimeTableModelInt#findByPK(long)
	 */
	public TimeTableDTO findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		Session session = null;
		TimeTableDTO dto = null;
		try {
			session = HibDataSource.getSession();
			dto = (TimeTableDTO) session.get(TimeTableDTO.class, pk);
		} catch (HibernateException e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by pk");
		} finally {
			session.close();
		}
		return dto;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * in.co.rays.model.TimeTableModelInt#search(in.co.rays.dto.TimeTableDTO,
	 * int, int)
	 */
	public List search(TimeTableDTO dto, int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model search Started");
		Session session = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(TimeTableDTO.class);
			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getCourseId() > 0) {
				criteria.add(Restrictions.eq("courseId", dto.getCourseId()));
			}

			if (dto.getSubjectId() > 0) {
				criteria.add(Restrictions.eq("subjectId", dto.getSubjectId()));
			}
			if (dto.getSemester() != null && dto.getSemester().length() > 0) {
				criteria.add(Restrictions.like("semester", dto.getSemester()));
			}
			/*
			 * if(dto.getExamDate()!=null){ Date date = new
			 * Date(dto.getExamDate().getTime());
			 * criteria.add(Restrictions.like("examDate", date)); }
			 * 
			 * 
			 */if (pageSize > 0) {
				criteria.setFirstResult((pageNo - 1) * pageSize);
				criteria.setMaxResults(pageSize);
			}
			list = criteria.list();
		} catch (HibernateException e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception in user search");
		} finally {
			session.close();
		}

		log.debug("Model search End");

		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * in.co.rays.model.TimeTableModelInt#search(in.co.rays.dto.TimeTableDTO)
	 */
	public List search(TimeTableDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return search(dto, 0, 0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.co.rays.model.TimeTableModelInt#list()
	 */
	public List list() throws ApplicationException {

		return list(0, 0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.co.rays.model.TimeTableModelInt#list(int, int)
	 */
	public List list(int pageNo, int pageSize) throws ApplicationException {
		Session session = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(TimeTableDTO.class);

			// if page size is greater than zero then apply pagination
			if (pageSize > 0) {
				pageNo = ((pageNo - 1) * pageSize) + 1;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}

			list = criteria.list();
		} catch (HibernateException e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in  Users list");
		} finally {
			session.close();
		}

		log.debug("Model list End");

		return list;

	}

}
