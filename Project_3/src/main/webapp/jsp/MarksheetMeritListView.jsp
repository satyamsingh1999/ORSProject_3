<%@page import="in.co.rays.controller.MarksheetMeritListCtl"%>
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


<form action="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>" method="post">


<% List list = ServletUtility.getList(request);

System.out.println(list);
 if (list.size()==0){ %>
 
	
        <div class="text-center">
			<td><input class="button" type="submit"  name="operation" value="<%=MarksheetListCtl.OP_BACK%>"></td>
			</div>
			<%}else{ %>
	 <h3 class="text-center default-text text-dark py-3"> Marksheet List:</h3>
	
	  
	   <div class="col-12 d-flex justify-content-center">
					<a class="btn btn-info" href="<%=ORSView.JASPER_CTL%>" target="blank">Click Here to
						Print MeritList</a>
						<% System.out.println("hi i am marksheet merit list view");%>
				</div>
	  <br>
	 
	  
      <div class="table-responsive " >
      <table class="table table-striped" width="100%">
      <thead class="thead-dark">
      <tr >
      <th class="text-center"><input type="checkbox" id="select_all" name="select">Select All</th>
                   <th>S.NO</th>
					<th>Roll No</th>
					<th>Name</th>
					<th>Maths</th>
					<th>Physics</th>
					<th>Chemistry</th>
					<th>Total</th>
					<th>Percentage%</th>
				    <th>Edit</th>
      </tr>
      </thead>
<%
	                int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;
                   
                    Iterator<MarksheetDTO> it = list.iterator();
                    while (it.hasNext()) {
                    	
                       dto = it.next();
               %>
				
               <tbody>
				<tr   class="<%=((index)%2==0)?"table-info":"table-danger"%>">
					<td class="text-center"><input type="checkbox" class="checkbox" name="ids" value="<%=dto.getId()%>"></td>
					<td class=""><%=index++%></td>
				    <td><%=dto.getRollNo()%></td>
					<td><%=dto.getName()%></td>
					<td><%=dto.getMaths()%></td>
					<td><%=dto.getPhysics()%></td>
					<td><%=dto.getChemistry()%></td>
	                <td><%=(dto.getChemistry()+dto.getMaths()+dto.getPhysics())%></td>
                    <td><%=(dto.getChemistry()+dto.getMaths()+dto.getPhysics())/3%></td>
					
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
			<td >
			<div class="text-center">
			<input  class="bg-warning text-white" style="font-size: 17px" type="submit"  name="operation" value="<%=MarksheetMeritListCtl.OP_BACK%>"></td>
			</div>
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