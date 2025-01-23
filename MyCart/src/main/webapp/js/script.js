document.addEventListener('DOMContentLoaded', function() {
    fetchCartItems(); // Fetch and display cart items when the page loads
});

function fetchCartItems() {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "RetrieveCartServlet", true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                console.log("Response received: ", xhr.responseText);
                var cartItems = JSON.parse(xhr.responseText);
                updateCartModal(cartItems); // Update the cart modal with fetched items
                updateCartCount(cartItems); // Update the cart item count
            } else {
                console.log("Failed to fetch cart items, status: ", xhr.status);
            }
        }
    };
    xhr.send();
}

// Modify existing functions to reflect cart items in the admin view
function updateCartModal(cartItems) {
    var cartItemsTable = document.getElementById('cart-items');
    if (!cartItemsTable) {
        console.error("Element with ID 'cart-items' not found.");
        return;
    }
    cartItemsTable.innerHTML = ''; // Clear existing content
    
    cartItems.forEach(function(item) {
        var row = document.createElement('tr');
        
        var productNameCell = document.createElement('td');
        productNameCell.textContent = item.product.pname;
        row.appendChild(productNameCell);
        
        var quantityCell = document.createElement('td');
        quantityCell.textContent = item.quantity;
        row.appendChild(quantityCell);
        
        var actualPriceCell = document.createElement('td');
        actualPriceCell.textContent = item.product.actualPrize;
        row.appendChild(actualPriceCell);
        
        var totalPriceCell = document.createElement('td');
        totalPriceCell.textContent = item.quantity * item.product.actualPrize;
        row.appendChild(totalPriceCell);
        
        var actionCell = document.createElement('td');
        var removeButton = document.createElement('button');
        removeButton.textContent = 'Remove';
        removeButton.className = 'btn btn-danger btn-sm';
        removeButton.onclick = function() {
            removeFromCart(item.id); // Call remove from cart function
        };
        actionCell.appendChild(removeButton);
        row.appendChild(actionCell);
        
        cartItemsTable.appendChild(row);
    });
}

function updateCartCount(cartItems) {
    var cartItemCount = document.querySelector(".cart-item");
    if (cartItemCount) {
        cartItemCount.textContent = `(${cartItems.length})`;
    } else {
        console.error("Element with class 'cart-item' not found.");
    }
}

function addToCart(productId) {
    fetchCartItemsForAddition(productId, function(isNewProduct) {
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "AddToCartServlet", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

        var userEmail = document.getElementById('user-email').value;
        var params = `productId=${productId}&quantity=1&userEmail=${encodeURIComponent(userEmail)}`;

        xhr.onreadystatechange = function() {
            if (xhr.readyState == 4 && xhr.status == 200) {
                if (isNewProduct) {
                    alert("Item added to cart successfully!");
                } else {
                    alert("Product is already added, quantity increased!");
                }
                fetchCartItems(); // Refresh the cart items
            } else if (xhr.readyState == 4) {
                alert("Failed to add item to cart.");
            }
        };
        xhr.send(params);
    });
}

function fetchCartItemsForAddition(productId, callback) {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "RetrieveCartServlet", true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var cartItems = JSON.parse(xhr.responseText);
            var isNewProduct = !cartItems.some(item => item.product.id === productId);
            callback(isNewProduct);
        }
    };
    xhr.send();
}

function removeFromCart(cartId) {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "RemoveFromCartServlet", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    var params = `cartId=${cartId}`;

    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            alert("Item removed from cart successfully!");
            fetchCartItems(); // Refresh the cart items
        } else if (xhr.readyState == 4) {
            alert("Failed to remove item from cart.");
        }
    };
    xhr.send(params);
}

function goToCheckout() {
    // Implement the checkout process here
}
