<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign Up</title>
<%@include file="component/common_css_js.jsp"%>
<style>
body {
	background-color: #f8f9fa;
}

.card {
	border-radius: 15px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.card-header {
	background-color: #41029f !important;
	color: white;
	border-radius: 15px 15px 0 0;
	text-align: center;
}

.form-control {
	border-radius: 10px;
}

.btn-custom {
	border-radius: 25px;
	width: 100px;
}
</style>
</head>
<body>

	<%@include file="component/navbar.jsp"%>

	<div class="container-fluid">
		<div class="row mt-5 justify-content-center">
			<div class="col-md-6 col-lg-4">
				<div class="card">
					<div class="card-header">
						<h3 class="my-3">User SignUp</h3>
					</div>
					<%@include file="component/Message.jsp"%>
					<div class="card-body px-5 py-4">

						<form action="RegisterServlet" method="post">
							<div class="form-group">
								<label for="firstname">First Name</label> <input name="fname"
									required type="text" class="form-control" id="firstname"
									placeholder="Enter First Name">
							</div>

							<div class="form-group">
								<label for="lastname">Last Name</label> <input name="lname"
									required type="text" class="form-control" id="lastname"
									placeholder="Enter Last Name">
							</div>

							<div class="form-group">
								<label for="email">Email</label> <input name="mail" required
									type="email" class="form-control" id="email"
									placeholder="Enter Email">
							</div>

							<div class="form-group">
								<label for="password">Password</label> <input name="pass"
									required type="password" class="form-control" id="password"
									placeholder="Enter Password">
							</div>

							<div class="form-group">
								<label for="phoneno">Phone No</label> <input name="mobno"
									required type="text" class="form-control" id="phoneno"
									placeholder="Enter Phone Number" pattern="\d{10}"
									title="Please enter a 10-digit phone number">
							</div>
							<span class="text-center d-block">If already registered <a
								href="UserLogin.jsp">Login here</a></span>
							<div class="text-center mt-4">
								<button type="submit" class="btn btn-outline-success btn-custom">Register</button>
								<button type="reset" class="btn btn-outline-warning btn-custom">Reset</button>
							</div>
						</form>

					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
