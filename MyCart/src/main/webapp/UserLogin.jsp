<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@include file="component/common_css_js.jsp" %>
</head>
<body>
<%@include file="component/navbar.jsp" %>
<div class="conainer mt-5">
<div class="row ">
<div class="col-md-4 offset-md-4">
<div class="card mt-3">
<div class="card-header blue text-white text-center">
<h3>User Login</h3>
</div>
<div class="card-body">
<%@include file="component/Message.jsp" %>
<form action="UserLoginServlet" method="post">
  <div class="form-group">
    <label for="exampleInputEmail1">Email address</label>
    <input required name="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
    <small id="emailHelp" class="form-text text-muted"></small>
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">Password</label>
    <input required name="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
  </div>
 <span class="text-center d-block">If not registered <a href="Register.jsp">Click here</a></span>
 
 <div class="container  text-center">
  <button type="submit" class=" blue btn btn-primary mt-3">Submit</button>
  </div>
</form>

</div>


</div>
</div>
</div>
</div>

</body>
</html>