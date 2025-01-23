<%@page import="com.ecommerce.entities.User"%>
<%@page session="true" %>
<%
    User currentuser = (User) session.getAttribute("current-user");
    String userType = currentuser != null ? currentuser.getUsertype() : "guest";
%>

<!-- Store user type in a hidden input field or JavaScript variable -->
<input type="hidden" id="user-type" value="<%= userType %>">

<!-- Cart Modal -->
<div class="modal fade" id="cart" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="cart-modal-title">Cart</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <div class="cart-body">
          <table class="table table-hover">
            <thead>
              <tr>
                <th>Product Name</th>
                <th>Quantity</th>
                <th>Actual Price</th>
                <th>Total Price</th>
                <th>Action</th>
              </tr>
            </thead>
            <tbody id="cart-items">
              <!-- Cart items will be dynamically inserted here -->
            </tbody>
          </table>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary checkout-btn" onclick="goToCheckout()">Checkout</button>
      </div>
    </div>
  </div>
</div>

<script>
document.addEventListener('DOMContentLoaded', function() {
    updateCartModalTitle(); // Update the cart modal title when the page loads

    $('#cart').on('show.bs.modal', function (event) {
        updateCartModalTitle(); // Update the cart modal title when the modal is shown
    });

    fetchCartItems(); // Fetch and display cart items when the page loads
});

function updateCartModalTitle() {
    var userType = document.getElementById('user-type').value;
    var cartModalTitle = document.getElementById('cart-modal-title');

    if (userType === 'admin') {
        cartModalTitle.textContent = 'Users Product Cart List';
    } else {
        cartModalTitle.textContent = 'Your Products In Cart';
    }
}

function goToCheckout() {
    window.location.href = 'CheckOut.jsp';
}
</script>
