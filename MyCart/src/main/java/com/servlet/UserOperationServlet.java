package com.servlet;



//UserOperationServlet.java
import com.ecommerce.helper.Factoryprovider;
import com.servlet.Dao.UserDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/UserOperationServlet")
public class UserOperationServlet extends HttpServlet {
 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     String operation = request.getParameter("operation");
		HttpSession session=request.getSession();

     if (operation != null && operation.equals("removeUser")) {
         int userId = Integer.parseInt(request.getParameter("userId"));
         UserDao userDao = new UserDao(Factoryprovider.getFactory());
         userDao.removeUser(userId);
			session.setAttribute("message1", "User Deleted Successfully ");

         response.sendRedirect("Admin.jsp");
     }
 }

 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     doPost(request, response);
 }
}

