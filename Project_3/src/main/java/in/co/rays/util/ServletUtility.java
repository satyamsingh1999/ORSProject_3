package in.co.rays.util;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.controller.BaseCtl;
import in.co.rays.controller.ORSView;
import in.co.rays.dto.BaseDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class ServletUtility.
 * @author Iterator
 * @version 1.0
 */
public class ServletUtility {
	
	/**
	 * Forward to given JSP/Servlet.
	 *
	 * @param page the page
	 * @param request the request
	 * @param response the response
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ServletException the servlet exception
	 */
    public static void forward(String page, HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher rd = request.getRequestDispatcher(page);
        rd.forward(request, response);
    }

    /**
     * Redirect to given JSP/Servlet.
     *
     * @param page the page
     * @param request the request
     * @param response the response
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws ServletException the servlet exception
     */
    public static void redirect(String page, HttpServletRequest request,
           HttpServletResponse response) throws IOException, ServletException {
      response.sendRedirect(page);
    }

    /**
     * Redirect to Application Error Handler Page.
     *
     * @param e the e
     * @param request the request
     * @param response the response
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws ServletException the servlet exception
     */
    public static void handleException(Exception e, HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("exception", e);
        response.sendRedirect(ORSView.ERROR_CTL);

    }

    /**
     * Gets error message from request.
     *
     * @param property the property
     * @param request the request
     * @return the error message
     */
    public static String getErrorMessage(String property,
            HttpServletRequest request) {
       String val = (String) request.getAttribute(property);
        if (val == null) {
            return "";
        } else {
        	return val;
        }
        }
    

    /**
     * Gets a message from request.
     *
     * @param property the property
     * @param request the request
     * @return the message
     */
    public static String getMessage(String property, HttpServletRequest request) {
        String val = (String) request.getAttribute(property);
        if (val == null) {
            return "";
        } else {
            return val;
        }
    }

    /**
     * Sets error message to request.
     *
     * @param msg the msg
     * @param request the request
     */
    public static void setErrorMessage(String msg, HttpServletRequest request) {
       request.setAttribute(BaseCtl.MSG_ERROR, msg);
    }

    /**
     * Gets error message from request.
     *
     * @param request the request
     * @return the error message
     */
    public static String getErrorMessage(HttpServletRequest request) {
        String val = (String) request.getAttribute(BaseCtl.MSG_ERROR);
        if (val == null) {
            return "";
        } else {
            return val;
        }
    }

    /**
     * Sets success message to request.
     *
     * @param msg the msg
     * @param request the request
     */
    public static void setSuccessMessage(String msg, HttpServletRequest request) {
        request.setAttribute(BaseCtl.MSG_SUCCESS, msg);
    }

    /**
     * Gets success message from request.
     *
     * @param request the request
     * @return the success message
     */
    public static String getSuccessMessage(HttpServletRequest request) {
        String val = (String) request.getAttribute(BaseCtl.MSG_SUCCESS);
        if (val == null) {
            return "";
        } else {
            return val;
        }
    }

    /**
     * Sets default DTO to request.
     *
     * @param dto the dto
     * @param request the request
     */
    public static void setDto(BaseDTO dto, HttpServletRequest request) {
        request.setAttribute("dto", dto);
    }

    /**
     * Gets default DTO from request.
     *
     * @param request the request
     * @return the dto
     */

    public static BaseDTO getDto(HttpServletRequest request) {
        return (BaseDTO) request.getAttribute("dto");
    }

    /**
     * Get request parameter to display. If value is null then return empty
     * string
     *
     * @param property the property
     * @param request the request
     * @return the parameter
     */

    public static String getParameter(String property,
            HttpServletRequest request) {
        String val = (String) request.getParameter(property);
        if (val == null) {
            return "";
        } else {
            return val;
        }
    }

    /**
     * Sets default List to request.
     *
     * @param list the list
     * @param request the request
     */
    public static void setList(List list, HttpServletRequest request) {
        request.setAttribute("list", list);
    }

    /**
     * Gets default list from request.
     *
     * @param request the request
     * @return the list
     */
    public static List getList(HttpServletRequest request) {
        return (List) request.getAttribute("list");
    }

    /**
     * Sets Page Number for List pages.
     *
     * @param pageNo the page no
     * @param request the request
     */
    public static void setPageNo(int pageNo, HttpServletRequest request) {
        request.setAttribute("pageNo", pageNo);
    }

    /**
     * Gets Page Number for List pages.
     *
     * @param request the request
     * @return the page no
     */
    public static int getPageNo(HttpServletRequest request) {
        return  (Integer) request.getAttribute("pageNo");
    }

    /**
     * Sets Page Size for list pages.
     *
     * @param pageSize the page size
     * @param request the request
     */
    public static void setPageSize(int pageSize, HttpServletRequest request) {
        request.setAttribute("pageSize", pageSize);
    }

    /**
     * Gets Page Size for List pages.
     *
     * @param request the request
     * @return the page size
     */
    public static int getPageSize(HttpServletRequest request) {
        return (Integer) request.getAttribute("pageSize");
    }


}
