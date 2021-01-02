<%@page import="java.text.SimpleDateFormat"%>
<%@page import="in.co.rays.dto.TimeTableDTO"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="in.co.rays.controller.TimeTableListCtl"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">

<style  >
.divider-text {
    position: relative;
    text-align: center;
    margin-top: 15px;
    margin-bottom: 15px;
}
.divider-text span {
    padding: 7px;
    font-size: 12px;
    position: relative;   
    z-index: 2;
}
.divider-text:after {
    content: "";
    position: absolute;
    width: 100%;
    border-bottom: 1px solid #ddd;
    top: 55%;
    left: 0;
    z-index: 1;
}

.btn-facebook {
    background-color: #405D9D;
    color: #fff;
}
.btn-twitter {
    background-color: #42AEEC;
    color: #fff;
}
</style>

</head>
<body background="/Project_3/jsp/p3.png">
<jsp:useBean id="dto" scope="request" class="in.co.rays.dto.TimeTableDTO" />
<div>
<%@include file="Header.jsp" %>
</div>

<div class="container-fluid">


<form action="<%=ORSView.TIME_TABLE_LIST_CTL%>" method="post">

   <%if(!ServletUtility.getSuccessMessage(request).equals("")){ %>
    <div class="alert alert-success alert-dismissible fade show">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <strong>Success!</strong><font color="green"> <%=ServletUtility.getSuccessMessage(request) %></font>
    </div>
    <%} %>                            
	
	<%if(!ServletUtility.getErrorMessage(request).equals("")){ %>
  <center><div class="alert alert-danger alert-dismissible fade show">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <strong>Error!</strong><font color="red"><%=ServletUtility.getErrorMessage(request) %></font>
  </div></center>

<%} %>                            
            <input type="hidden" name="id" value="<%=dto.getId()%>">
            <input type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy" value="<%=dto.getModifiedBy()%>">
            <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(dto.getCreatedDateTime())%>">
            <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(dto.getModifiedDateTime())%>">


<% 


   List courseList=(List)request.getAttribute("courseList");
	List subjectList=(List)request.getAttribute("subjectList");
	
	

    HashMap<String,String> map=new HashMap<String,String>();
        map.put("1", "1");
		map.put("2", "2");
		map.put("3", "3");
		map.put("4", "4");
		map.put("5", "5");
		map.put("6", "6");
		map.put("7", "7");
		map.put("8", "8");
		map.put("9", "9");
		map.put("10", "10");

	
	List list = ServletUtility.getList(request);
	
	
	%>
	<% if (list.size()==0){ %>
        <div class="text-center">
			<td><input class="button" type="submit"  name="operation" value="<%=TimeTableListCtl.OP_BACK%>"></td>
			</div>
			<%}else{ %>
	 <h3 class="text-center default-text text-dark py-3"> TimeTable List:</h3>
	 
	 <br>
	  <div class="row">
	  <div class="col-sm-2"></div>
	  <label class="form-check-label" for="check2" style = "width:50px">
      Course:
      </label>
	  <div class="col-sm-2"><%=HTMLUtility.getList("course",String.valueOf(dto.getCourseId()),courseList) %></div>
	 <label class="form-check-label" for="check2" style = "width:50px">
      Subject:
      </label>
	  <div class="col-sm-2"><%=HTMLUtility.getList("subject",String.valueOf(dto.getSubjectId()),subjectList) %></div>
	 <label class="form-check-label" for="check2" style = "width:50px">
      Semester:
      </label>
	  <div class="col-sm-2"><%=HTMLUtility.getList("semester",dto.getSemester(),map)%></div>
	  
	  
	  <div class="col-sm-2">
	  <input   class="bg-success text-white"style="font-size: 17px" type="submit"  name="operation" value="<%=TimeTableListCtl.OP_SEARCH%>">
	  <input  class="bg-warning text-white" style="font-size: 17px" type="submit"  name="operation" value="<%=TimeTableListCtl.OP_RESET%>">
	  </div> 
	  <div class="col-sm-2"></div>
	  </div>
	  
	  <br>
	  <br>
	  
	  
      <div class="table-responsive " >
      <table class="table table-striped" width="100%">
      <thead class="thead-dark">
      <tr >
      <th class="text-center"><input type="checkbox" id="select_all" name="select">Select All</th>
                     <th>S.NO</th>
					 <th>Course Name</th>
					 <th>Subject Name</th>
				     <th>Semester</th>
				     <th>Exam Date</th>
					 <th>Exam Time</th>
					 <th>Description</th>
					 <th>Edit</th>
      </tr>
      </thead>
      <%
	                int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;
                    int nextPageSize = DataUtility.getInt
                   		 (request.getAttribute("nextlistsize").toString());
                   
                    Iterator<TimeTableDTO> it = list.iterator();
                    	
              /*  SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
                String date=sdf.format(dto.getExamDate()); */

                while (it.hasNext()) {
                	dto=(TimeTableDTO)it.next();

                %>
               <tbody>
				<tr  class="<%=((index)%2==0)?"table-info":"table-danger"%>">
					<td class="text-center"><input type="checkbox" class="checkbox" name="ids" value="<%=dto.getId()%>"></td>
					<td class=""><%=index++%></td>									
					<td align=""><%=dto.getCourseName()%></td>
					<td align=""><%=dto.getSubjectName()%></td>
					<td align=""><%=dto.getSemester()%></td>
				
					<td align=""><%=DataUtility.getDateString(dto.getExamDate())%></td>
					<td align=""><%=dto.getExamTime()%></td>
					<td align=""><%=dto.getDescription() %></td>
					<td  ><a class="text-dark" href="TimeTableCtl?id=<%=dto.getId()%>">Edit</a></td>
				</tr>
				</tbody>
				<%
                    }
                %>
			</table>
			
			</div>
			<div class="table-responsive " >
			<table   width="100%">
			<tr>
			<td><input  class="bg-primary text-white" style="font-size: 17px" type="submit"  name="operation" value="<%=TimeTableListCtl.OP_PREVIOUS%>"  <%=pageNo > 1 ? "" : "disabled"%>></td>
			<td><input  class="bg-success text-white" style="font-size: 17px" type="submit"  name="operation" value="<%=TimeTableListCtl.OP_NEW%>"></td>
			<td><input  class="bg-danger text-white" style="font-size: 17px" type="submit"  name="operation" value="<%=TimeTableListCtl.OP_DELETE%>"></td>
			
			<td align="right"><input   class="bg-primary text-white" style="font-size: 17px" type="submit"  name="operation" value="<%=TimeTableListCtl.OP_NEXT%>" <%=(nextPageSize != 0) ? "" : "disabled"%>  >
			</td>
			
			
			</tr>
			
			
			</table>
			
				</div>
						
			<input type="hidden" name="pageNo" value="<%=pageNo%>"> 
			<input type="hidden" name="pageSize" value="<%=pageSize%>">
			
					
			<%} %>

 <br><br>     <br><br> 
</form>
</div>

<%@include file="Footer.jsp" %>

</body>
</html>