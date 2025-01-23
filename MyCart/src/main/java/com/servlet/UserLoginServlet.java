package com.servlet;


import java.io.IOException;
import java.io.PrintWriter;

import com.ecommerce.entities.User;
import com.ecommerce.helper.Factoryprovider;
import com.servlet.Dao.UserDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public UserLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
		PrintWriter out=response.getWriter();
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		UserDao userdao=new UserDao(Factoryprovider.getFactory());
		User user=userdao.getUserByEmailAndPassword(email, password);
		HttpSession session=request.getSession();
		if(user==null) {
			session.setAttribute("message1", "Invalid Details!! ");
			response.sendRedirect("UserLogin.jsp");
			return;
		}
		else {
			session.setAttribute("current-user", user);
			if(user.getUsertype().equals("normal")) {
				response.sendRedirect("User.jsp");
			}
			else {
				session.setAttribute("message1", "Invalid Details!! ");
				response.sendRedirect("UserLogin.jsp");
				return;
				}
			}
	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	

}