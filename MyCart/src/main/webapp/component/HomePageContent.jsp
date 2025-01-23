
<script>
function addToCart(productId, productName, productPrice) {
    <% User currentUser = (User) session.getAttribute("current-user"); %>
    var isLoggedIn = <%= (currentUser != null) %>;
    var userEmail = "<%= currentUser != null ? currentUser.getEmail() : "" %>";

    console.log("Is Logged In: " + isLoggedIn);
    console.log("User Email: " + userEmail);

    if (!isLoggedIn) {
        alert("Please login to add items to the cart.");
        window.location.href = "UserLogin.jsp";
    } else {
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "AddToCartServlet", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                alert("Item added to cart successfully!");
                fetchCartItems(); // Refresh the cart items
            } else if (xhr.readyState === 4) {
                alert("Failed to add item to cart.");
            }
        };
        xhr.send("productId=" + encodeURIComponent(productId) + "&userEmail=" + encodeURIComponent(userEmail) + "&quantity=1");
    }
}
</script>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.ecommerce.entities.User, com.ecommerce.helper.Factoryprovider, com.ecommerce.entities.Category, com.servlet.Dao.CategoryDao, java.util.List, com.servlet.Dao.ProductDao, com.ecommerce.helper.Helper, com.ecommerce.entities.Product, java.net.URLEncoder"%>
<div class="container-fluid mt-3">
    <div class="row">
        <div class="col-md-12">
            <div class="row mt-3 mx-3">
                <%
                ProductDao dao = new ProductDao(Factoryprovider.getFactory());
                String cat = request.getParameter("category");
                List<Product> list = (cat == null || cat.trim().equals("all")) ? dao.getAllProducts()
                        : dao.getAllProductsByCategoryId(Integer.parseInt(cat.trim()));
                for (Product p : list) {
                    String productNameEscaped = URLEncoder.encode(p.getPname(), "UTF-8").replace("+", "%20");
                %>
                <div class="col-md-4 mb-4">
                    <div class="card product-card">
                        <div class="container text-center">
                            <img src="images/products/<%=p.getImage()%>"
                                class="card-img-top m-2" alt="<%=p.getPname()%>">
                        </div>
                        <div class="card-body">
                            <h5 class="card-title"><%=p.getPname()%></h5>
                            <p class="card-text"><%=Helper.get10words(p.getDescription())%></p>
                            <button class="btn btn-link mt-auto" data-toggle="modal"
                                data-target="#descriptionModal<%=p.getPid()%>">Read
                                More</button>
                        </div>
                        <div class="card-footer d-flex justify-content-between mx-2">
                            <button class="btn blue text-white" style="width: 100px;"
                                onclick='addToCart(<%=p.getPid()%>, "<%=productNameEscaped%>", <%=p.getActualPrize()%>)'>Add
                                to cart</button>
                            <button class="btn btn-outline-primary">
                                &#8377;<%=p.getActualPrize()%>/- <span
                                    class="text-secondary discount-label">&#8377;<%=p.getPrice()%>,
                                    <%=p.getDiscount()%>% off
                                </span>
                            </button>
                        </div>
                    </div>
                </div>

                <div class="modal fade" id="descriptionModal<%=p.getPid()%>"
                    tabindex="-1" role="dialog"
                    aria-labelledby="descriptionModalLabel<%=p.getPid()%>"
                    aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title"
                                    id="descriptionModalLabel<%=p.getPid()%>"><%=p.getPname()%></h5>
                                <button type="button" class="close" data-dismiss="modal"
                                    aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <%=p.getDescription()%>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary"
                                    data-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                </div>
                <%
                }
                %>
            </div>
            <%
            if (list.isEmpty()) {
            %>
            <h3>Product is not available</h3>
            <%
            }
            %>
        </div>
    </div>
</div>