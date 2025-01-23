package com.servlet;

import java.io.IOException;

import com.ecommerce.entities.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LogoutServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Get the existing session, do not create a new one

        if (session != null) {
            User user = (User) session.getAttribute("current-user");

            if (user != null) {
                session.invalidate(); // Invalidate the session

                if ("normal".equals(user.getUsertype())) {
                    response.sendRedirect("UserLogin.jsp");
                } else if ("admin".equals(user.getUsertype())) {
                    response.sendRedirect("AdminLogin.jsp");
                } else {
                    response.sendRedirect("index.jsp"); // Fallback redirection
                }
            } else {
                response.sendRedirect("index.jsp"); // If user is null, redirect to the home page
            }
        } else {
            response.sendRedirect("index.jsp"); // If session is null, redirect to the home page
        }
    }
}
