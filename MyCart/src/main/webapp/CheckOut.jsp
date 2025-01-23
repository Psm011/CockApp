<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Checkout</title>
<%@ include file="component/common_css_js.jsp"%>
<%@ include file="component/navbar.jsp"%>
<%@include file="component/common_models.jsp"%>
</head>
<body>

	<div class="container px-5">
    <div class="col-md-5 mx-auto">
        <div class="card mt-5">
            <div class="card-header blue text-white text-center">
                <h3>Your Details For Order</h3>
            </div>
            <div class="card-body">
                <form action="OrderServlet" method="post">
                    <div class="form-group">
                        <label for="FirstName">First Name</label>
                        <input type="text" class="form-control" id="FirstName" name="FirstName" required>
                    </div>
                    <div class="form-group">
                        <label for="LastName">Last Name</label>
                        <input type="text" class="form-control" id="LastName" name="LastName" required>
                    </div>
                    <div class="form-group">
                        <label for="exampleInputEmail1">Email</label>
                        <input type="email" class="form-control" id="exampleInputEmail1" name="email" required>
                    </div>
                    <div class="form-group">
                        <label for="mobile">Mobile No</label>
                        <input type="text" class="form-control" id="mobile" name="mobile" pattern="\d{10}" title="Please enter a 10-digit phone number" required>
                    </div>
                    <div class="form-group">
                        <label for="exampleFormControlTextarea1">Your Shipping Address</label>
                        <textarea class="form-control" id="exampleFormControlTextarea1" name="address" rows="3" required></textarea>
                    </div>
                    <div class="text-center">
                        <button type="submit" class="btn btn-outline-success">Order Now</button>
                        <button type="button" class="btn btn-outline-primary" onclick="window.location.href='index.jsp'">Continue Shopping</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
	

</body>
</html>
