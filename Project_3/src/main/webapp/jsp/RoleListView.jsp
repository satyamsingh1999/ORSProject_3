<%@page import="in.co.rays.controller.RoleListCtl"%>
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
<jsp:useBean id="dto" scope="request" class="in.co.rays.dto.RoleDTO" />
<div>
<%@include file="Header.jsp" %>
</div>

<div class="container-fluid">


<form action="<%=ORSView.ROLE_LIST_CTL%>" method="post">

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
List roleList=(List)request.getAttribute("roleList");

System.out.println(list);
 if (list.size()==0){ %>
	
        <div class="text-center">
			<td><input class="button" type="submit"  name="operation" value="<%=RoleListCtl.OP_BACK%>"></td>
			</div>
			<%}else{ %>
	 <h3 class="text-center default-text text-dark py-3"> Role List:</h3>
	 
	  <br>
	  <div class="row justify-content-center "  >
	  <div class="col-sm-2"></div>
	  <label class="form-check-label" for="check2" style = "width:50px">
       Role:
      </label>
       <div class="col-sm-2"> <%=HTMLUtility.getList("roleId", String.valueOf(dto.getId()), roleList)%></div>
      	 
	  <div class="col-sm-2">
	  <input   class="bg-success text-white"style="font-size: 17px" type="submit"  name="operation" value="<%=RoleListCtl.OP_SEARCH%>">
	  <input  class="bg-warning text-white" style="font-size: 17px" type="submit"  name="operation" value="<%=RoleListCtl.OP_RESET%>">
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
					<th>Name</th>
					<th>Description</th>					
					<th>Edit</th>
      </tr>
      </thead>
     <%
	                int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;
/*                     int nextPageSize = DataUtility.getInt
 *//*                    		 (request.getAttribute("nextListSize").toString());
 */                   
                    Iterator<RoleDTO> it = list.iterator();
                    while (it.hasNext()) {
                    	
                        dto = it.next();
               %>
               <tbody>
				<tr   class="<%=((index)%2==0)?"table-info":"table-danger"%>">
					<td class="text-center"><input type="checkbox" class="checkbox" name="ids" value="<%=dto.getId()%>"></td>
					<td class=""><%=index++%></td>
					<td><%=dto.getName()%></td>
					<td><%=dto.getDescription()%></td>
					
					
					<td><a class="text-dark" href="RoleCtl?id=<%=dto.getId()%>">Edit</a></td>
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
			<td><input  class="bg-primary text-white" style="font-size: 17px" type="submit"  name="operation" value="<%=RoleListCtl.OP_PREVIOUS%>"  <%=pageNo > 1 ? "" : "disabled"%>></td>
			<td><input  class="bg-success text-white" style="font-size: 17px" type="submit"  name="operation" value="<%=RoleListCtl.OP_NEW%>"></td>
			<td><input  class="bg-danger text-white" style="font-size: 17px" type="submit"  name="operation" value="<%=RoleListCtl.OP_DELETE%>"></td>
			
			<td align="right"><input   class="bg-primary text-white" style="font-size: 17px" type="submit"  name="operation" value="<%=RoleListCtl.OP_NEXT%>"  <%=pageNo > 1 ? "" : "disabled"%>>
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