package in.co.rays.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.dto.BaseDTO;
import in.co.rays.dto.UserDTO;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.ServletUtility;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseCtl.
 * @author Iterator
 * @version 1.0
 
 */
public abstract class BaseCtl extends HttpServlet {
	
	/** The Constant OP_SAVE. */
	public static final String OP_SAVE = "Save";
	
	/** The Constant OP_BACK. */
	public static final String OP_BACK = "Back";
	
	/** The Constant OP_CANCEL. */
	public static final String OP_CANCEL = "Cancel";
	
	/** The Constant OP_DELETE. */
	public static final String OP_DELETE = "Delete";
	
	/** The Constant OP_RESET. */
	public static final String OP_RESET = "Reset";
	
	/** The Constant OP_UPDATE. */
	public static final String OP_UPDATE = "Update";
	
	/** The Constant OP_LIST. */
	public static final String OP_LIST = "List";
	
	/** The Constant OP_SEARCH. */
	public static final String OP_SEARCH = "Search";
	
	/** The Constant OP_VIEW. */
	public static final String OP_VIEW = "View";
	
	/** The Constant OP_NEXT. */
	public static final String OP_NEXT = "Next";
	
	/** The Constant OP_PREVIOUS. */
	public static final String OP_PREVIOUS = "Previous";
	
	/** The Constant OP_NEW. */
	public static final String OP_NEW = "New";
	
	/** The Constant OP_GO. */
	public static final String OP_GO = "Go";
	
	/** The Constant OP_SEND. */
	public static final String OP_SEND = "Send";

	/** Success message key constant. */
	public static final String MSG_SUCCESS = "success";

	/** Error message key constant. */
	public static final String MSG_ERROR = "error";

	/**
	 * Validates input data entered by User.
	 *
	 * @param request the request
	 * @return true, if successful
	 */
	protected boolean validate(HttpServletRequest request) {
		return true;
	}

	/**
	 * Loads list and other data required to display at HTML form.
	 *
	 * @param request the request
	 */
	protected void preload(HttpServletRequest request) {
	}

	/**
	 * Populates DTO object from request parameters.
	 *
	 * @param request the request
	 * @return the base DTO
	 */
	protected BaseDTO populateDTO(HttpServletRequest request) {
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Load the preloaded data required to display at HTML form

/*System.out.println("in base ctl..............");*/
		
		preload(request);

		String op = DataUtility.getString(request.getParameter("operation"));

		// Check if operation is not DELETE, VIEW, CANCEL, and NULL then
		// perform input data validation
/*System.out.println("inside service method ");
*/		if (DataValidator.isNotNull(op) && !OP_CANCEL.equalsIgnoreCase(op) && !OP_VIEW.equalsIgnoreCase(op)
				&& !OP_DELETE.equalsIgnoreCase(op) && !OP_RESET.equalsIgnoreCase(op)) {
			// Check validation, If fail then send back to page with error
			// messages

/*			System.out.println("inside if");
*/
			if (!validate(request)) {
/*				System.out.println("in validate");
*/				BaseDTO dto = (BaseDTO) populateDTO(request);
				ServletUtility.setDto(dto, request);
				ServletUtility.forward(getView(), request, response);
				return;
			}
		}
/*		System.out.println("in base ctl..............123");
*/		super.service(request, response);
	}

	/**
	 * Populates Generic attributes Date Time Objects in dto.	
	 * @param dto the dto
	 * @param request the request
	 * @return the base DTO
	 */
	protected BaseDTO populateDateTime(BaseDTO dto, HttpServletRequest request) {

		String createdBy = request.getParameter("createdBy");
		String modifiedBy = null;

		UserDTO userbean = (UserDTO) request.getSession().getAttribute("user");

		if (userbean == null) {
			// If record is created without login
			createdBy = "root";
			modifiedBy = "root";
		} else {

			modifiedBy = userbean.getLoginId();

			// If record is created first time
			if ("null".equalsIgnoreCase(createdBy) || DataValidator.isNull(createdBy)) {
				createdBy = modifiedBy;
			}

		}

		dto.setCreatedBy(createdBy);
		dto.setModifiedBy(modifiedBy);

		long cdt = DataUtility.getLong(request.getParameter("createdDatetime"));

		if (cdt > 0) {
			dto.setCreatedDateTime(DataUtility.getTimestamp(cdt));
		} else {
			dto.setCreatedDateTime(DataUtility.getCurrentTimestamp());
		}

		dto.setModifiedDateTime(DataUtility.getCurrentTimestamp());

		return dto;
	}

	/**
	 * Returns the VIEW page of this Controller.
	 *
	 * @return the view
	 */
	protected abstract String getView();



}
