<%@page import="com.ecommerce.entities.Product"%>
<%@ page import="com.ecommerce.entities.User"%>
<%@ page import="java.util.List"%>
<%@ page import="com.servlet.Dao.UserDao"%>
<%@ page import="com.ecommerce.helper.Factoryprovider"%>
<%@ page import="com.ecommerce.entities.Category"%>
<%@ page import="com.servlet.Dao.CategoryDao"%>
<%@ page import="com.servlet.Dao.ProductDao"%>
<%
User user = (User) session.getAttribute("current-user");
if (user == null) {
	session.setAttribute("message1", "You are not logged in! Please Login");
	response.sendRedirect("AdminLogin.jsp");
	return;
} else {
	if (!user.getUsertype().equals("admin")) {
		session.setAttribute("message1", "Only admin can access this page");
		response.sendRedirect("AdminLogin.jsp");
		return;
	}
}

CategoryDao categoryDao = new CategoryDao(Factoryprovider.getFactory());
List<Category> categories = categoryDao.getCategories();

UserDao userDao = new UserDao(Factoryprovider.getFactory());
List<User> users = userDao.getAllUsers();

ProductDao productDao = new ProductDao(Factoryprovider.getFactory());
List<Product> products = productDao.getAllProducts();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dashboard</title>
	<%@ include file="component/navbar.jsp"%>

<%@ include file="component/common_css_js.jsp"%>
<%@ include file="component/common_models.jsp"%>
		<%@ include file="component/Message.jsp"%>

</head>
<body>

	<div class="container admin">

		<div class="row mt-3">
			<!-- First column: Number of Users -->
			<div class="col-md-4">
				<div class="card " style="height: 250px;" data-toggle="modal"
					data-target="#user-list-modal">
					<div class="card-body text-center">
						<div class="container">
							<img class="img-fluid " style="height: 150px;"
								src="images/team.png" alt="user_icon">
						</div>
						<h3><%=users.stream().filter(u -> u.getUsertype().equals("normal")).count()%></h3>
						<h3 class="text-uppercase text-muted">Users</h3>
					</div>
				</div>
			</div>

			<!-- Second column: Add category -->
			<div class="col-md-4">
				<div class="card" style="height: 250px;" data-toggle="modal"
					data-target="#add-category-modal">
					<div class="card-body text-center">
						<div class="container">
							<img class="img-fluid" style="height: 150px;"
								src="images/menu.png" alt="category_icon">
						</div>
						<div class="container mt-3">
							<h3 class="text-uppercase text-muted">Add Category</h3>
						</div>
					</div>
				</div>
			</div>

			<!-- Third column: Add Product -->
			<div class="col-md-4">
				<div class="card" style="height: 250px;" data-toggle="modal"
					data-target="#add-Product-modal">
					<div class="card-body text-center">
						<div class="container">
							<img class="img-fluid" style="height: 150px;"
								src="images/shopping-cart.png" alt="product_icon">
						</div>
						<div class="container mt-3">
							<h3 class="text-uppercase text-muted">Add Products</h3>
						</div>
					</div>
				</div>
			</div>
			<!-- Fourth column: Remove category -->
			<div class="col-md-6 mt-4">
				<div class="card" style="height: 250px;" data-toggle="modal"
					data-target="#remove-category-modal">
					<div class="card-body text-center">
						<div class="container">
							<img class="img-fluid" style="height: 150px;"
								src="images/Category.png"  alt="remove_category_icon">
						</div>
						<div class="container mt-3">
							<h3 class="text-uppercase text-muted">Remove Category</h3>
						</div>
					</div>
				</div>
			</div>
			<!-- Fifth column: Remove Product -->
			<div class="col-md-6 mt-4">
				<div class="card" style="height: 250px;" data-toggle="modal"
					data-target="#remove-product-modal">
					<div class="card-body text-center">
						<div class="container">
							<img class="img-fluid" style="height: 150px;"
								src="images/remove.png"  alt="remove_product_icon">
						</div>
						<div class="container mt-3">
							<h3 class="text-uppercase text-muted">Remove Product</h3>
						</div>
					</div>
				</div>
			</div>

		</div>

		<!-- Modal for displaying user list -->
		<div class="modal fade" id="user-list-modal" tabindex="-1"
			role="dialog" aria-labelledby="userListModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header blue text-white">
						<h5 class="modal-title" id="userListModalLabel">User List</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<table class="table table-bordered">
							<thead>
								<tr>
									<th>ID</th>
									<th>FirstName</th>
									<th>Last Name</th>
									<th>Email</th>
									<th>User type</th>
									<th>Actions</th>
								</tr>
							</thead>
							<tbody>
								<%
								for (User userItem : users) {
									if (userItem.getUsertype().equals("normal")) {
								%>
								<tr>
									<td><%=userItem.getUserid()%></td>
									<td><%=userItem.getFirstname()%></td>
									<td><%=userItem.getLastname()%></td>
									<td><%=userItem.getEmail()%></td>
									<td><%=userItem.getUsertype()%></td>
									<td>
										<form action="UserOperationServlet" method="post"
											style="display: inline;">
											<input type="hidden" name="operation" value="removeUser">
											<input type="hidden" name="userId"
												value="<%=userItem.getUserid()%>">
											<button type="submit" class="btn btn-danger">Remove</button>
										</form>
									</td>
								</tr>
								<%
								}
								}
								%>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>

		<!-- Other content -->
		<div class="row mt-4">


			<div class="col-md-6"></div>
		</div>
	</div>

	<!-- Modal for add category -->
	<div class="modal fade" id="add-category-modal" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header blue text-white">
					<h5 class="modal-title" id="exampleModalLabel">Fill Category
						Details</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form action="ProductOperationServlet" method="post">
						<input type="hidden" name="operation" value="addcategory" />
						<div class="form-group">
							<label for="title">Title</label> <input name="title" required
								type="text" class="form-control" id="title"
								placeholder="Enter Title">
						</div>
						<div class="form-group">
							<label for="description">Description</label>
							<textarea style="height: 100px" name="description"
								class="form-control" id="description"
								placeholder="Enter Description"></textarea>
						</div>
						<div class="text-center mt-4">
							<button type="submit" class="btn btn-outline-success btn-custom">Add
								Category</button>
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">Close</button>
						</div>
					</form>
				</div>

			</div>
		</div>
	</div>
	<!-- Modal for add product -->
	<div class="modal fade" id="add-Product-modal" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header blue text-white">
					<h5 class="modal-title" id="exampleModalLabel">Fill Product
						Details</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form action="ProductOperationServlet" method="post"
						enctype="multipart/form-data">
						<input type="hidden" name=operation " value="addproduct" />
						<div class="form-group">
							<label for="productName"> Name</label> <input name="productName"
								required type="text" class="form-control" id="productName"
								placeholder="">
						</div>

						<div class="form-group">
							<label for="Quantity"> Quantity</label> <input name="Quantity"
								required type="number" class="form-control" id="Quantity"
								placeholder="">
						</div>

						<div class="form-group">
							<label for="price">Price</label> <input name="price" required
								type="number" class="form-control" id="price" placeholder="">
						</div>
						<div class="form-group">
							<label for="discount">Discount</label> <input name="discount"
								required type="number" class="form-control" id="discount"
								placeholder="">
						</div>
						<div class="form-group">
							<label for="description">Description</label>
							<textarea style="height: 100px" name="description"
								class="form-control" id="description" placeholder=""></textarea>
						</div>
						<!-- getting categories by invoking CategoryDao  -->

						<div class="form-group">
							<label for="catid">Select Category</label> <select id="catid"
								name="catid" class="form-control">
								<%
								for (Category c : categories) {
								%>
								<option value="<%=c.getCategoryid()%>"><%=c.getCategoryTitle()%></option>
								<%
								}
								%>
							</select>
						</div>



						<div class="form-group">
							<label for="productPhoto">Add image</label> <input
								name="productPhoto" required type="file"
								class="form-control-file" id="productPhoto">
						</div>

						<div class="text-center mt-4">
							<button type="submit" class="btn btn-outline-success btn-custom">Add
								Product</button>
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">Close</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>


</body>
</html>
<!-- Modal for remove category -->
<div class="modal fade" id="remove-category-modal" tabindex="-1"
	role="dialog" aria-labelledby="removeCategoryModalLabel"
	aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header blue text-white">
				<h5 class="modal-title" id="removeCategoryModalLabel">Remove
					Category</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form action="CategoryProductOperationServlet" method="post">
					<input type="hidden" name="operation" value="removecategory" />
					<div class="form-group">
						<label for="removeCategoryid">Select Category</label> <select
							id="removeCategoryid" name="categoryid" class="form-control">
							<%
							for (Category c : categories) {
							%>
							<option value="<%=c.getCategoryid()%>"><%=c.getCategoryTitle()%></option>
							<%
							}
							%>
						</select>
					</div>
					<div class="text-center mt-4">
						<button type="submit" class="btn btn-outline-danger btn-custom">Remove
							Category</button>
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Close</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<!-- Remove product modal -->
<div class="modal fade" id="remove-product-modal" tabindex="-1"
	role="dialog" aria-labelledby="removeProductModalLabel"
	aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header blue text-white">
				<h5 class="modal-title" id="removeProductModalLabel">Remove
					Product</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form action="CategoryProductOperationServlet" method="post">
					<input type="hidden" name="operation" value="removeproduct" />
					<div class="form-group">
						<label for="productSelect">Select Product to Remove</label> <select
							class="form-control" id="productSelect" name="productId">
							<option selected>--- Select Product ---</option>
							<%
							for (Product product : products) {
							%>
							<option value="<%=product.getPid()%>">
								<%=product.getPname()%> %>
							</option>
							<%
							}
							%>
						</select>
					</div>
					<div class="text-center mt-4">
						<button type="submit" class="btn btn-outline-danger btn-custom">Remove
							Product</button>
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Close</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
</body>
</html>
