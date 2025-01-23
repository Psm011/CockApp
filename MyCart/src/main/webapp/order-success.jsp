<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order Success</title>
    <link rel="stylesheet" type="text/css" href="path/to/bootstrap.css">
    <%@ include file="component/navbar.jsp"%>
    <%@ include file="component/common_css_js.jsp"%>
    <%@ include file="component/common_models.jsp"%>
    <style>
        .success-message {
            text-align: center;
            margin-top: 50px;
        }
        .success-icon {
            font-size: 50px;
            color: #28a745; /* Bootstrap success color */
        }
    </style>
</head>
<body>
    <div class="container mt-5 success-message">
        <div class="alert alert-success">
            <i class="fas fa-check-circle success-icon"></i> <!-- Font Awesome success icon -->
            <h2>Order Successful!</h2>
            <p>Your order has been placed successfully!</p>
            <p>Thank you for shopping with us. You will receive a confirmation email shortly.</p>
            <a href="index.jsp" class="btn btn-primary">Continue Shopping</a>
        </div>
    </div>
    
    <!-- Include Font Awesome for the check icon -->
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
</body>
</html>
