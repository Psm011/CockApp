package com.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.ecommerce.entities.Cart;
import com.ecommerce.entities.Product;
import com.ecommerce.entities.User;
import com.ecommerce.helper.Factoryprovider;
import com.servlet.Dao.CartDao;
import com.servlet.Dao.ProductDao;
import com.google.gson.Gson;

@WebServlet("/RetrieveCartServlet")
public class RetrieveCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Get existing session, do not create new
        User user = (User) session.getAttribute("current-user");

        if (user == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        CartDao cartDao = new CartDao(Factoryprovider.getFactory());
        ProductDao productDao = new ProductDao(Factoryprovider.getFactory());
        List<Cart> cartItems;

        if ("admin".equals(user.getUsertype())) {
            // Admin can view all cart items
            cartItems = cartDao.getAllCartItems();
        } else {
            // Regular user can view their own cart items
            cartItems = cartDao.getCartItemsByUserEmail(user.getEmail());
        }

        List<Map<String, Object>> cartDetails = new ArrayList<>();

        for (Cart cart : cartItems) {
            Map<String, Object> cartDetail = new HashMap<>();
            cartDetail.put("id", cart.getId());
            cartDetail.put("productId", cart.getProductId());
            cartDetail.put("userEmail", cart.getUserEmail());
            cartDetail.put("quantity", cart.getQuantity());

            // Fetch product details
            Product product = productDao.getProductById(cart.getProductId());
            if (product != null) {
                Map<String, Object> productDetail = new HashMap<>();
                productDetail.put("pname", product.getPname());
                productDetail.put("actualPrize", product.getActualPrize());
                cartDetail.put("product", productDetail);
            }

            cartDetails.add(cartDetail);
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String json = new Gson().toJson(cartDetails);
        response.getWriter().write(json);
    }
}
