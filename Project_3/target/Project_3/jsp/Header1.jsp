<%@page import="in.co.rays.dto.RoleDTO"%>
<%@page import="in.co.rays.dto.UserDTO"%>
<%@page import="in.co.rays.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
 
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
 
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
 
   <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
   <style type="text/css">

</style>
<script src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js"></script>
<script type="text/javascript"
src="<%=ORSView.APP_CONTEXT%>/js/CheckBox11.js"></script>

<link rel="stylesheet"
href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com//jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script>
$(function() {
$("#date").datepicker({

changeMonth : true,
changeYear : true,
maxDate : 0,
//minDate: 0+1,
//beforeShowDay: noSunday,
//beforeShowDay1: noSunday1,

yearRange : "-67:",
//defaultDate:"01/01/1999"
});
});
</script>
</head>
<body>
<%@page import="in.co.rays.controller.LoginCtl"%>

<%@page import="in.co.rays.controller.ORSView"%>
<%
UserDTO userDto = (UserDTO) session.getAttribute("user");

boolean userLoggedIn = userDto != null;

String welcomeMsg = "Hi, ";
String byee = "byee";
if (userLoggedIn) {
String role = (String) session.getAttribute("role");
welcomeMsg += userDto.getFirstName() + " (" + role + ")";

} else {
byee += welcomeMsg += "Guest";
}
%>
<style>
 .navbar-dark .navbar-nav .nav-link {
    color: #f8f9fa;
}
 </style>

<nav class="navbar navbar-expand-sm bg-secondary navbar-dark">
  <a class="navbar-brand" href="<%=ORSView.WELCOME_CTL%>"><img src="<%=ORSView.APP_CONTEXT%>/img/custom.png" width="80" height="50"></a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
  </button>
   <div class="collapse navbar-collapse" id="collapsibleNavbar">
    <ul class="navbar-nav ml-auto mr-5">
   
   
   
   
            <%
            if (userLoggedIn) {
        %>
          <%
if (userDto.getRoleId() == RoleDTO.STUDENT) {
%>

<!-- Dropdown -->
    <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
        Marksheet
      </a>
      <div class="dropdown-menu">
        <a class="dropdown-item" href="<%=ORSView.MARKSHEET_CTL%>">Add Marksheet</a>
        <a class="dropdown-item" href="<%=ORSView.MARKSHEET_LIST_CTL%>">Marksheet List</a>
     
      </div>
    </li>

<!-- Dropdown -->
    <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
        User
      </a>
      <div class="dropdown-menu">
        <a class="dropdown-item" href="<%=ORSView.MY_PROFILE_CTL%>">My Profile</a>
        <a class="dropdown-item" href="<%=ORSView.CHANGE_PASSWORD_CTL%>">Change Password</a>
     
      </div>
    </li>
<%
}else if(userDto.getRoleId()==RoleDTO.ADMIN){
%>
   
       
          <!-- Dropdown -->
    <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
       <i class='fas fa-user'></i>&nbsp; User
      </a>
      <div class="dropdown-menu">
        <a class="dropdown-item" href="<%=ORSView.USER_CTL%>"><i class='fas fa-plus-square'></i>&nbsp;Add User</a>
        <a class="dropdown-item" href="<%=ORSView.USER_LIST_CTL%>"><i class='fa fa-list-alt'></i>&nbsp;User List</a>
     
      </div>
    </li>
   
   
      <!-- Dropdown -->
    <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
       <i class='fas fa-file-alt' ></i>&nbsp;Marksheet
      </a>
      <div class="dropdown-menu">
        <a class="dropdown-item" href="<%=ORSView.MARKSHEET_CTL%>"><i class='fas fa-plus-square' ></i>&nbsp;Add Marksheet</a>
        <a class="dropdown-item" href="<%=ORSView.MARKSHEET_LIST_CTL%>"><i class='fa fa-list-alt' ></i>&nbsp;Marksheet List</a>
       <a class="dropdown-item" href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>"><i class='fa fa-list-alt' ></i>&nbsp;Marksheet Merit List</a>
        <a class="dropdown-item" href="<%=ORSView.GET_MARKSHEET_CTL%>"><i class='fas fa-plus-square' ></i>&nbsp;Get Marksheet</a>
     
      </div>
    </li>
   
   
      <!-- Dropdown -->
    <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown" class="fonta">
        <i class='fas fa-user-circle'></i>&nbsp;Role
      </a>
      <div class="dropdown-menu">
        <a class="dropdown-item" href="<%=ORSView.ROLE_CTL%>"><i class='fas fa-plus-square'></i>&nbsp;Add Role</a>
        <a class="dropdown-item" href="<%=ORSView.ROLE_LIST_CTL%>"><i class='fa fa-list-alt'></i>&nbsp;Role List</a>
     
      </div>
    </li>
   
     <!-- Dropdown -->
    <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
        <i class='fas fa-university'></i>&nbsp;College
      </a>
      <div class="dropdown-menu">
        <a class="dropdown-item" href="<%=ORSView.COLLEGE_CTL%>"> <i class='fas fa-plus-square'></i>&nbsp;Add College</a>
        <a class="dropdown-item" href="<%=ORSView.COLLEGE_LIST_CTL%>"> <i class='fa fa-list-alt'></i>&nbsp;College List</a>
     
      </div>
    </li>
   
   
      <!-- Dropdown -->
    <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
         <i class='fa fa-book'></i>&nbsp;Course
      </a>
      <div class="dropdown-menu">
        <a class="dropdown-item" href="<%=ORSView.COURSE_CTL%>"><i class='fas fa-plus-square'></i>&nbsp;Add Course</a>
        <a class="dropdown-item" href="<%=ORSView.COURSE_LIST_CTL%>"><i class='fa fa-list-alt'></i>&nbsp;Course List</a>
     
      </div>
    </li>
   
   
       <!-- Dropdown -->
    <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
         <i class='fas fa-user-graduate'></i>&nbsp;Student
      </a>
      <div class="dropdown-menu">
        <a class="dropdown-item" href="<%=ORSView.STUDENT_CTL%>"><i class='fas fa-plus-square'></i>&nbsp;Add Student</a>
        <a class="dropdown-item" href="<%=ORSView.STUDENT_LIST_CTL%>"> <i class='fa fa-list-alt'></i>&nbsp;Student List</a>
     
      </div>
    </li>
   
       <!-- Dropdown -->
    <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
        <i class='fas fa-user-tie'></i>&nbsp; Faculty
      </a>
      <div class="dropdown-menu">
        <a class="dropdown-item" href="<%=ORSView.FACULTY_CTL%>"><i class='fas fa-plus-square'></i>&nbsp; Add Faculty</a>
        <a class="dropdown-item" href="<%=ORSView.FACULTY_LIST_CTL%>"><i class='fa fa-list-alt'></i>&nbsp; Faculty List</a>
     
      </div>
    </li>
   
     <!-- Dropdown -->
    <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
        <i class='fas fa-clock'></i>&nbsp; TimeTable
      </a>
      <div class="dropdown-menu">
        <a class="dropdown-item" href="<%=ORSView.TIME_TABLE_CTL%>"><i class='fas fa-plus-square'></i>&nbsp;Add TimeTable</a>
        <a class="dropdown-item" href="<%=ORSView.TIME_TABLE_LIST_CTL%>"><i class='fa fa-list-alt'></i>&nbsp;TimeTable List</a>
     
      </div>
    </li>
   
   
   
   
       
   
      <!-- Dropdown -->
    <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
       <i class='fas fa-book'></i>&nbsp;Subject
      </a>
      <div class="dropdown-menu">
        <a class="dropdown-item" href="<%=ORSView.SUBJECT_CTL%>"><i class='fas fa-plus-square'></i>&nbsp;Add Subject</a>
        <a class="dropdown-item" href="<%=ORSView.SUBJECT_LIST_CTL%>"><i class='fa fa-list-alt'></i>&nbsp;Subject List</a>
     
      </div>
    </li>
   
   
         <%
            }
            }
        %>
       
         <!-- Dropdown -->
    <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
     <i class='fas fa-user'></i>&nbsp;<b><%=welcomeMsg %></b></a>
      <div class="dropdown-menu">
     
      <%
if(userLoggedIn){
      %>
     
      <a class="dropdown-item" href="<%=ORSView.MY_PROFILE_CTL%>"><i class='fas fa-graduation-cap'></i>&nbsp;My Profile </a>
       <a class="dropdown-item" href="<%=ORSView.CHANGE_PASSWORD_CTL%>"><i class='fas fa-edit'></i>&nbsp;Change Password </a>
       <a class="dropdown-item" href="<%=ORSView.JAVA_DOC_VIEW %>" target="blank"><i class='fas fa-clone'></i>&nbsp;Java Doc</a>
        <a class="dropdown-item" href="<%=ORSView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>"><i class='fas fa-sign-out-alt'></i>&nbsp;Logout</a>
       
       
      </div>
    </li>  
                   
                   
             <%
                } else {
            %>
            <a class="dropdown-item" href="<%=ORSView.LOGIN_CTL%>"><i class='fas fa-sign-in-alt'></i>&nbsp;Login</a>
             <a class="dropdown-item" href="<%=ORSView.USER_REGISTRATION_CTL%>"><i class='fas fa-user-plus'></i>&nbsp;User Registration</a>
             <a class="dropdown-item" href="<%=ORSView.FORGET_PASSWORD_CTL%>"><i class='fa fa-lock'></i>&nbsp;Forget Password</a>
           
             <%
     }
 %></ul>
</div>
</nav>

</body>
</html>