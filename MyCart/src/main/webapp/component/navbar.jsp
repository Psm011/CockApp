<%@ page import="com.ecommerce.entities.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ecommerce.helper.Factoryprovider" %>
<%@ page import="com.servlet.Dao.CategoryDao" %>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<link rel="stylesheet" href="css/style.css">
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Your head content here -->
</head>
<body>
    <!-- Your body content here -->
    <!-- Include the cart modal somewhere in your HTML -->
</body>
</html>

<nav class="navbar navbar-expand-lg navbar-dark blue">
    <div class="container">
        <a class="navbar-brand" href="index.jsp">MyCart</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse"
                data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="index.jsp">Home <span class="sr-only">(current)</span></a>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
                       role="button" data-toggle="dropdown" aria-haspopup="true"
                       aria-expanded="false" style="color: white;"> Categories </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="index.jsp">All Products</a>
                        <% 
                            CategoryDao cdao = new CategoryDao(Factoryprovider.getFactory());
                            List<Category> clist = cdao.getCategories();
                            for (Category category : clist) { 
                        %>
                            <a class="dropdown-item" href="index.jsp?category=<%= category.getCategoryid() %>">
                                <%= category.getCategoryTitle() %>
                            </a>
                        <% } %>
                    </div>
                </li>
            </ul>

            <%-- Right side of navbar --%>
            <ul class="navbar-nav ml-auto">
                <% HttpSession session3 = request.getSession(false);
                if (session3 != null && session3.getAttribute("current-user") != null) { %>
                    <li class="nav-item active">
                        <a class="nav-link" href="#!" data-toggle="modal" data-target="#cart" onclick="fetchCartItems()">
                            <i class="fas fa-shopping-cart" style="font-size: 25px;"></i>
                            <span class="cart-item">(0)</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link btn btn-danger" href="LogoutServlet">Logout</a>
                    </li>
                <% } else { %>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="loginDropdown"
                           role="button" data-toggle="dropdown" aria-haspopup="true"
                           aria-expanded="false" style="color: white;"> Login </a>
                        <div class="dropdown-menu" aria-labelledby="loginDropdown">
                            <a class="dropdown-item" href="AdminLogin.jsp">Admin Login</a>
                            <a class="dropdown-item" href="UserLogin.jsp">User Login</a>
                        </div>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="Register.jsp">Register</a>
                    </li>
                <% } %>
            </ul>
        </div>
    </div>
</nav>