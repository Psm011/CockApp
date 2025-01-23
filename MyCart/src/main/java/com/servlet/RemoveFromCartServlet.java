package com.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.ecommerce.entities.Cart;
import com.ecommerce.helper.Factoryprovider;
import com.servlet.Dao.CartDao;
import org.hibernate.Session;
import org.hibernate.Transaction;

@WebServlet("/RemoveFromCartServlet")
public class RemoveFromCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cartId = Integer.parseInt(request.getParameter("cartId"));

        Session hibernateSession = Factoryprovider.getFactory().openSession();
        Transaction tx = null;

        try {
            tx = hibernateSession.beginTransaction();
            CartDao cartDao = new CartDao(Factoryprovider.getFactory());
            cartDao.removeCartById(cartId);
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
