<%@page import="in.co.rays.controller.StudentListCtl"%>
<%@page import="in.co.rays.controller.MarksheetCtl"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
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
<script type="text/javascript">
function isNumberKey(evt)
{
   var charCode = (evt.which) ? evt.which : event.keyCode
   if (charCode > 31 && (charCode < 48 || charCode > 57))
      return false;

   return true;
}


  </script>
</head>
<body background="/Project_3/jsp/p3.png">

<% 	String uri = (String)request.getAttribute("uri");%>
<jsp:useBean id="dto" scope="request" class="in.co.rays.dto.MarksheetDTO" />
<%@include file="Header.jsp" %>
<br>
<div class="container">

<div class="card bg-secondary text-white mx-auto" style="max-width: 400px; " >
<article class="card-body mx-auto" style="max-width: 400px;">
	
	
	<form action="<%=ORSView.MARKSHEET_CTL%>" method="post">
	<!-- getting role list for preload -->
<%List studentList=(List)request.getAttribute("studentList"); %>
	
	
                        <%long id=DataUtility.getLong(request.getParameter("id")); %>
                       
                           <h3 class="text-center default-text py-3"><%=(id==0)? "Add Marksheet": "Update Marksheet" %></h3>
                            
                          
                            <!--Body-->
                            
                            		<%if(!ServletUtility.getSuccessMessage(request).equals("")){ %>
    <div class="alert alert-success alert-dismissible fade show">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <strong>Success!</strong><%=ServletUtility.getSuccessMessage(request) %>
    </div>
    <%} %>                            
	
	<%if(!ServletUtility.getErrorMessage(request).equals("")){ %>
<div class="alert alert-danger">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <strong>Error!</strong><font><%=ServletUtility.getErrorMessage(request) %></font>
  </div>
<%} %>                            
		
            <input type="hidden" name="id" value="<%=dto.getId()%>">
            <input type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy" value="<%=dto.getModifiedBy()%>">
            <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(dto.getCreatedDateTime())%>">
            <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(dto.getModifiedDateTime())%>">
         
         <h6 style="color: #20B2AA">Roll No<font color="red">*</font></h6>
         
         <div class="input-group mb-3">                   
	<div class="form-group input-group">
		<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-user"></i> </span>
		 </div>
        <input name="rollNo" class="form-control" placeholder="Enter rollno" type="text" value="<%=DataUtility.getStringData(dto.getRollNo())%>">
    </div>
    <font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("rollNo",request) %> </font>
    </div>
     <!-- form-group// -->
    
       <%String student=HTMLUtility.getList("studentId",String.valueOf(dto.getStudentId()),studentList); %>
    
    <h6 style="color: #20B2AA">Student Name<font color="red">*</font></h6>
        
    <div class="input-group mb-3">
    <div class="form-group input-group">
		<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-user-graduate"></i> </span>
		 </div>
<%=student%>
    </div>
    <font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("student",request) %> </font>
</div>     <!-- form-group// -->
    
    <h6 style="color: #20B2AA">Physics<font color="red">*</font></h6>
    
    <div class="input-group mb-3">
     <div class="form-group input-group">
    	<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-file-alt"></i> </span>
		</div>
    	<input name="physics" onkeypress="return isNumberKey(event)" class="form-control" placeholder="Enter physics" type="text" value="<%=DataUtility.getStringData(dto.getPhysics()).equals("0")?"":DataUtility.getStringData(dto.getPhysics())%>" >
    </div><font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("mobileNumber",request) %> </font> 
    </div>
    <!-- form-group// -->
    
     <h6 style="color: #20B2AA">Chemistry<font color="red">*</font></h6>
    
    <div class="input-group mb-3">
    <div class="form-group input-group">
    	<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-file-alt"></i> </span>
		 </div>
        <input name="chemistry" onkeypress="return isNumberKey(event)" class="form-control" placeholder="Enter chemistry" type="text" value="<%=DataUtility.getStringData(dto.getChemistry()).equals("0")?"":DataUtility.getStringData(dto.getChemistry())%>">
    </div> <font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("chemistry",request) %> </font>
    </div>
    <!-- form-group// -->
    
    <h6 style="color: #20B2AA">Maths<font color="red">*</font></h6>
    
    <div class="input-group mb-3">
   <div class="form-group input-group">
    	<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-file-alt"></i> </span>
		</div>
        <input name="maths" onkeypress="return isNumberKey(event)" class="form-control" placeholder="Enter maths" type="text" value="<%=DataUtility.getStringData(dto.getMaths()).equals("0")?"":DataUtility.getStringData(dto.getMaths())%>">
    </div> <font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("maths",request) %> </font>
    </div>
    <!-- form-group// -->
    
  
      <%if (id>0){ %>                                   
    <div class="form-group " align="center">
        <input type="submit" class="bg-success text-white" name="operation" value="<%=MarksheetCtl.OP_UPDATE%>">   
                <input type="submit" class="bg-warning text-white"  name="operation" value="<%=MarksheetCtl.OP_CANCEL%>">
     </div> <!-- form-group// -->  
   
   <%}else{ %> 
   <div class="form-group " align="center">
        <input type="submit" class="bg-success text-white" name="operation" value="<%=MarksheetCtl.OP_SAVE%>">   
                <input type="submit" class="bg-warning text-white"  name="operation" value="<%=MarksheetCtl.OP_RESET%>">
     </div> <!-- form-group// -->  
   <%} %>
      
</form>
</article>
</div> <!-- card.// -->

</div> 
<!--container end.//-->
<br><br>
<br>
<%@include file="Footer.jsp" %>
</body>
</html>