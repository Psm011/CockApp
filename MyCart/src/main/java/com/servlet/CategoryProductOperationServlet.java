package com.servlet;

import com.ecommerce.entities.Category;
import com.servlet.Dao.CategoryDao;
import com.servlet.Dao.ProductDao;
import com.ecommerce.helper.Factoryprovider;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/CategoryProductOperationServlet")
public class CategoryProductOperationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CategoryProductOperationServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operation = request.getParameter("operation");

        if (operation.equals("removecategory")) {
            int categoryId = Integer.parseInt(request.getParameter("categoryid"));

            CategoryDao categoryDao = new CategoryDao(Factoryprovider.getFactory());
            categoryDao.removeCategory(categoryId);

            request.getSession().setAttribute("message1", "Category removed successfully");
            response.sendRedirect("Admin.jsp");

        } else if (operation.equals("removeproduct")) {
            int productId = Integer.parseInt(request.getParameter("productId"));

            ProductDao productDao = new ProductDao(Factoryprovider.getFactory());
            productDao.removeProduct(productId);

            request.getSession().setAttribute("message1", "Product removed successfully");
            response.sendRedirect("Admin.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
