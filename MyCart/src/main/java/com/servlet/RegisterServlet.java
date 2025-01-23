package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ecommerce.helper.Factoryprovider;
import com.ecommerce.entities.User;

@WebServlet("/register")  // Added annotation to map the servlet
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RegisterServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String firstname = request.getParameter("fname");
            String lastname = request.getParameter("lname");
            String email = request.getParameter("mail");
            String password = request.getParameter("pass");
            String phoneno = request.getParameter("mobno");
            PrintWriter out = response.getWriter();

            // Check if email is already registered
            Session session = Factoryprovider.getFactory().openSession();
            Query<User> query = session.createQuery("FROM User WHERE email = :email", User.class);
            query.setParameter("email", email);
            User existingUser = query.uniqueResult();

            if (existingUser != null) {
                // Email already registered
                HttpSession session2 = request.getSession();
                session2.setAttribute("message2", "Registration failed: User with this email id already exists");
                response.sendRedirect("Register.jsp");
                session.close();
                return;
            }

            // Creating object to store data
            User user = new User(firstname, lastname, email, password, phoneno, "default.jpg", "normal");
            Transaction t = session.beginTransaction();
            int userid = (int) session.save(user);
            t.commit();
            session.close();

            HttpSession session2 = request.getSession();
            session2.setAttribute("message1", "Registration Successful. User id is " + userid);
            response.sendRedirect("Register.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            HttpSession session2 = request.getSession();
            session2.setAttribute("message2", "Registration failed: User with this email id already exists " );
            response.sendRedirect("Register.jsp");
        }
    }
}
