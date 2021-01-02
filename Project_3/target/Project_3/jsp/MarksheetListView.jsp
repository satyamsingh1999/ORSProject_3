<%@page import="in.co.rays.model.ModelFactory"%>
<%@page import="in.co.rays.model.MarksheetModelInt"%>
<%@page import="in.co.rays.dto.MarksheetDTO"%>
<%@page import="in.co.rays.controller.MarksheetListCtl"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
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
<jsp:useBean id="dto" scope="request" class="in.co.rays.dto.MarksheetDTO" />
<div>
<%@include file="Header.jsp" %>
</div>

<div class="container-fluid">


<form action="<%=ORSView.MARKSHEET_LIST_CTL%>" method="post">

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



<% List list = ServletUtility.getList(request);
List studentList=(List)request.getAttribute("studentList");

System.out.println(list);
 if (list.size()==0){ %>
	
        <div class="text-center">
			<td><input class="button" type="submit"  name="operation" value="<%=MarksheetListCtl.OP_BACK%>"></td>
			</div>
			<%}else{ %>
	 <h3 class="text-center default-text text-dark py-3"> Marksheet List:</h3>
	 
	  <br>
	 
	  <div class="row">
	  <div class="col-sm-2"></div>&ensp;&ensp;&ensp;
	  <label class="form-check-label" for="check2" style = "width:50px">
     Student:
      </label>&ensp;
	  <div class="col-sm-2"><%=HTMLUtility.getList("studentId", String.valueOf(dto.getStudentId()), studentList) %>
</div>&ensp;

<label class="form-check-label" for="check2" style = "width:50px">
      RollNo:
      </label>
	  <div class="col-sm-2"> <input placeholder="Email" type="text" id="defaultForm-email" name="rollNo" class="border"  value="<%=ServletUtility.getParameter("rollNo", request)%>">
</div>
	 
	  <div class="col-sm-2">
	  <input   class="bg-success text-white"style="font-size: 17px" type="submit"  name="operation" value="<%=MarksheetListCtl.OP_SEARCH%>">
	  <input  class="bg-warning text-white" style="font-size: 17px" type="submit"  name="operation" value="<%=MarksheetListCtl.OP_RESET%>">
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
					<th>RollNo</th>
					<th>Name</th>
					<th>Physics</th>
					<th>Chemistry</th>
					<th>Maths</th>
					<th>Total</th>
					<th>Percentage(%)</th>
					<th>Grade</th>
					<th>Result</th>
					<th>Edit</th>
      </tr>
      </thead>
      <%
                MarksheetModelInt s = ModelFactory.getInstance().getMarksheetModel();
				List l = s.list();
				int count = l.size();
                    int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize)+1;
                    int nextPageSize = DataUtility.getInt(request.getAttribute("nextListSize").toString());
                      

                     /* list = ServletUtility.getList(request); */
                    Iterator<MarksheetDTO> it = list.iterator();
                    while (it.hasNext()) {

						 dto = it.next();
						String grade = null;
						String result = "<font color='green'>Pass</font>";
						String div="";
						long p = DataUtility.getLong(dto.getPhysics()+"");
						long c = DataUtility.getLong(dto.getChemistry()+"");
						long m = DataUtility.getLong(dto.getMaths()+"");
						long totalMarks = (p + c + m);
						
						float percentage=(float)totalMarks/3;
                        percentage=percentage*100;
                        percentage=Math.round(percentage);
                        percentage=percentage/100;
                       int avg=(int)(p+c+m)/3;
                        if(avg>=80&&!(p<=32||c<=32||m<=32))
                        {
                            grade="A+";
                        }
                        else if(avg>60 && avg<=80&&!(p<=32||c<=32||m<=32))
                        {
                        	grade="A";
                        }
                        else if(avg>40 && avg<=60&&!(p<=32||c<=32||m<=32))
                        {
                        	grade="B";
                        }
                        else if(avg<40&&!(p<=32||c<=32||m<=32))
                        {
                        		
                        	grade="C";
                        }else{
                        	grade="D";
                        	result = "<font color='red'>Fail</font>";
                        }
					
						
					
				%>
				
               <tbody>
				<tr   class="<%=((index)%2==0)?"table-info":"table-danger"%>">
					<td class="text-center"><input type="checkbox" class="checkbox" name="ids" value="<%=dto.getId()%>"></td>
					<td class=""><%=index++%></td>
					<td><%=dto.getRollNo()%></td>
					<td><%=dto.getName()%></td>
					
					<td><%=dto.getPhysics()<33?dto.getPhysics()+"<font color='red'>*</font>":dto.getPhysics()%></td>
					<td><%=dto.getChemistry()<33?dto.getChemistry()+"<font color='red'>*</font>":dto.getChemistry()%></td>
					<td><%=dto.getMaths()<33?dto.getMaths()+"<font color='red'>*</font>":dto.getMaths()%></td>
					<td><%=totalMarks%></td>
					<td><%=percentage%></td>
					<td><%=grade%></td>
					<td><%=result%></td>
					
					<td><a class="text-dark" href="MarksheetCtl?id=<%=dto.getId()%>">Edit</a></td>
				</tr>
				</tbody>
				<%
                    }
                %>
			</table>
			
			</div>
			<div class="table-responsive " >
			<table width="100%">
			<tr>
			<td><input  class="bg-primary text-white" style="font-size: 17px" type="submit"  name="operation" value="<%=MarksheetListCtl.OP_PREVIOUS%>"  <%=pageNo > 1 ? "" : "disabled"%>></td>
			<td><input  class="bg-success text-white" style="font-size: 17px" type="submit"  name="operation" value="<%=MarksheetListCtl.OP_NEW%>"></td>
			<td><input  class="bg-danger text-white" style="font-size: 17px" type="submit"  name="operation" value="<%=MarksheetListCtl.OP_DELETE%>"></td>
			
			<td align="right"><input   class="bg-primary text-white" style="font-size: 17px" type="submit"  name="operation" value="<%=MarksheetListCtl.OP_NEXT%>" <%=(nextPageSize != 0) ? "" : "disabled"%>  >
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
</body>
<%@include file="Footer.jsp" %>
</html>