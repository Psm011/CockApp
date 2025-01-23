package com.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.ecommerce.entities.Order;
import com.ecommerce.entities.User;
import com.ecommerce.helper.Factoryprovider;
import com.servlet.Dao.OrderDao;

@WebServlet("/CompletePaymentServlet")
public class CompletePaymentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("current-user");

        // Create OrderDao instance
        OrderDao orderDao = new OrderDao(Factoryprovider.getFactory());

        // Update payment status
        Order order = orderDao.getOrderByUser(currentUser);
        if (order != null) {
            order.setPaymentStatus("Complete");
            orderDao.updateOrder(order);

            session.setAttribute("message", "Payment completed successfully! Your order has been placed.");
        } else {
            session.setAttribute("message", "No pending orders found for this user.");
        }

        response.sendRedirect("order-success.jsp");
    }
}
