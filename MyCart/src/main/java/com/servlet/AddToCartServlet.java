package com.servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.ecommerce.entities.Cart;
import com.ecommerce.entities.User;
import com.ecommerce.helper.Factoryprovider;
import com.servlet.Dao.CartDao;
import org.hibernate.Session;
import org.hibernate.Transaction;

@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Get existing session, do not create new
        User user = (User) session.getAttribute("current-user");

        if (user == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        int productId = Integer.parseInt(request.getParameter("productId"));
        String userEmail = user.getEmail();
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        Session hibernateSession = Factoryprovider.getFactory().openSession();
        Transaction tx = null;

        try {
            tx = hibernateSession.beginTransaction();
            CartDao cartDao = new CartDao(Factoryprovider.getFactory());
            Cart cartItem = cartDao.getCartByProductIdAndUserEmail(productId, userEmail);

            if (cartItem == null) {
                cartItem = new Cart(productId, userEmail, quantity);
            } else {
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
            }

            cartDao.saveOrUpdateCart(cartItem);
            tx.commit();
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } finally {
            hibernateSession.close();
        }
    }
}
