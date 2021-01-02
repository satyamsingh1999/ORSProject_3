package in.co.rays.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.dto.BaseDTO;
import in.co.rays.dto.CollegeDTO;
import in.co.rays.dto.MarksheetDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.model.MarksheetModelInt;
import in.co.rays.model.ModelFactory;
import in.co.rays.model.StudentModelInt;
import in.co.rays.util.DataUtility;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

// TODO: Auto-generated Javadoc
/**
 * The Class MarksheetListCtl.
 * @author Iterator
 * @version 1.0
 */
@WebServlet(name="MarksheetListCtl",urlPatterns={"/ctl/MarksheetListCtl"})
public class MarksheetListCtl extends BaseCtl {
	
	 /** The log. */
 	private static Logger log = Logger.getLogger(CollegeListCtl.class);
	 
	 
	 /* (non-Javadoc)
 	 * @see in.co.rays.controller.BaseCtl#preload(javax.servlet.http.HttpServletRequest)
 	 */
 	@Override
	protected void preload(HttpServletRequest request) {

		 StudentModelInt model=ModelFactory.getInstance().getStudentModel();
		 try {
			List studentList=  model.list();
			request.setAttribute("studentList",studentList );
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
		 
	 }

	    /* (non-Javadoc)
    	 * @see in.co.rays.controller.BaseCtl#populateDTO(javax.servlet.http.HttpServletRequest)
    	 */
    	@Override
	    protected BaseDTO populateDTO(HttpServletRequest request) {
	    	MarksheetDTO dto = new MarksheetDTO();
	    	
	    	
	        dto.setStudentId(DataUtility.getLong(request.getParameter("studentId")));

	        dto.setRollNo(DataUtility.getString(request.getParameter("rollNo")));

	        return dto;
	    }


	    
	    /* (non-Javadoc)
    	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
    	 */
    	@Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        int pageNo = 1;
	        int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

	        MarksheetDTO dto = (MarksheetDTO) populateDTO(request);
	        MarksheetModelInt model =ModelFactory.getInstance().getMarksheetModel();

	        List list = null;
	        List next=null;

	        try {
	            list = model.search(dto, pageNo, pageSize);
	            next = model.search(dto, pageNo+1, pageSize);
	        } catch (ApplicationException e) {
	            log.error(e);
	            ServletUtility.handleException(e, request, response);
	            return;
	        }

	        if (list == null || list.size() == 0) {
	            ServletUtility.setErrorMessage("No record found ", request);
	        } if(next==null||next.size()==0){
	        	request.setAttribute("nextListSize", 0);
	        }else{
	        	request.setAttribute("nextListSize", next.size());
	        }

/*	        ServletUtility.setDto(dto, request);
*/	        ServletUtility.setList(list, request);
	        ServletUtility.setPageNo(pageNo, request);
	        ServletUtility.setPageSize(pageSize, request);
	        ServletUtility.forward(getView(), request, response);
   
	    }
	
	    
	    /* (non-Javadoc)
    	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
    	 */
    	@Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	    	
	        log.debug("MarksheetListCtl doPost Start");
	        List list = null;
	        List next =null;
	        int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
	        int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

	        pageNo = (pageNo == 0) ? 1 : pageNo;
	        pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;
	        MarksheetDTO dto = ( MarksheetDTO) populateDTO(request);
	        String op = DataUtility.getString(request.getParameter("operation"));
	        // get the selected checkbox ids array for delete list
	        String ids[] = request.getParameterValues("ids");
	        MarksheetModelInt model = ModelFactory.getInstance().getMarksheetModel();
	        try {

	            if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op)
	                    || "Previous".equalsIgnoreCase(op)) {

	                if (OP_SEARCH.equalsIgnoreCase(op)) {
	                    pageNo = 1;
	                } else if (OP_NEXT.equalsIgnoreCase(op)) {
	                    pageNo++;
	                } else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
	                    pageNo--;
	                }

	            } else if (OP_NEW.equalsIgnoreCase(op)) {
	                ServletUtility.redirect(ORSView.MARKSHEET_CTL, request, response);
	                return;
	            
	            }else if(OP_RESET.equalsIgnoreCase(op))
	    		{
	  			  ServletUtility.redirect(ORSView.MARKSHEET_LIST_CTL, request, response);	
	  			  return;
	  		}

	            
	            else if (OP_DELETE.equalsIgnoreCase(op)) {
	                pageNo = 1;
	                if (ids != null && ids.length > 0) {
	                    MarksheetDTO deletedto = new MarksheetDTO();
	                    for (String id : ids) {
	                    	
	                    	long idnew=DataUtility.getLong(id);
	                    	
	                        deletedto.setId(idnew);
	                        
	                        model.delete(deletedto);
	                        
	                        ServletUtility.setErrorMessage("Delete data Successfully", request);
	                    }
	                } else if(OP_BACK.equalsIgnoreCase(op)){
	                	ServletUtility.redirect(ORSView.MARKSHEET_LIST_CTL, request, response);
	                }else{
	                    ServletUtility.setErrorMessage("Select at least one record", request);
	                }
	            }
	            list = model.search(dto, pageNo, pageSize);
	            next=model.search(dto, pageNo+1, pageSize);
	            ServletUtility.setList(list, request);
	            if (list == null || list.size() == 0&&!OP_DELETE.equalsIgnoreCase(op)) {
	                ServletUtility.setErrorMessage("No record found ", request);
	            }
	            if(next==null||next.size()==0){
	            	request.setAttribute("nextListSize", 0);
	            }else{
	            	request.setAttribute("nextListSize", next.size());
	            }
/*	            ServletUtility.setDto(dto, request);
*/	            ServletUtility.setList(list, request);
	            ServletUtility.setPageNo(pageNo, request);
	            ServletUtility.setPageSize(pageSize, request);
	            ServletUtility.forward(getView(), request, response);

	        } catch (ApplicationException e) {
	            log.error(e);
	            ServletUtility.handleException(e, request, response);
	            return;
	        }
	        log.debug("MarkseetListCtl doGet End");

	    }
	    
	    
	    /* (non-Javadoc)
    	 * @see in.co.rays.controller.BaseCtl#getView()
    	 */
    	@Override
	 protected String getView() {
		return ORSView.MARKSHEET_LIST_VIEW;
	}
	
	
	
}
