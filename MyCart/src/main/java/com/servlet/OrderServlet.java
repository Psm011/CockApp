package com.servlet;

import java.io.IOException;

import com.ecommerce.entities.Order;
import com.ecommerce.entities.Orders;
import com.ecommerce.entities.User;
import com.ecommerce.helper.Factoryprovider;
import com.servlet.Dao.OrderDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get user session
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("current-user");

        // Get form parameters
        String FirstName = request.getParameter("FirstName");
        String LastName = request.getParameter("LastName");
        String email = request.getParameter("email");
        String Mobile = request.getParameter("mobile");
        String address = request.getParameter("address");

        // Create order object
        Order order = new Order();
        order.setUser(currentUser);
        order.setFirstName(FirstName);
        order.setLastName(LastName);
        order.setEmail(email);
        order.setMobile(Mobile);
        order.setAddress(address);

        order.setPaymentStatus("Incomplete");

        // Save order to database
        OrderDao orderDao = new OrderDao(Factoryprovider.getFactory());
        orderDao.saveOrder(order);
     // Redirect to payment page or display success message
        session.setAttribute("message", "Order placed successfully! Complete the payment to finalize your order.");
        response.sendRedirect("payment.jsp");
    }
}