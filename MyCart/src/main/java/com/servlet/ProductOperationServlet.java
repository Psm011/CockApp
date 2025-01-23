package com.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import com.ecommerce.entities.Category;
import com.ecommerce.entities.Product;
import com.ecommerce.helper.Factoryprovider;
import com.servlet.Dao.CategoryDao;
import com.servlet.Dao.ProductDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

@WebServlet("/ProductOperationServlet")
@MultipartConfig
public class ProductOperationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ProductOperationServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PrintWriter out = response.getWriter();
            String op = request.getParameter("operation");

            if (op.trim().equals("addcategory")) {
                String name = request.getParameter("title");
                String description = request.getParameter("description");
                description = description.replaceAll("\\r?\\n", "<br>").trim();
                Category category = new Category();
                category.setCategoryTitle(name);
                category.setCategoryDescription(description);

                CategoryDao dao = new CategoryDao(Factoryprovider.getFactory());
                int catid = dao.saveCategory(category);
                HttpSession session = request.getSession();
                session.setAttribute("message1", "Category Saved Successfully");
                response.sendRedirect("Admin.jsp");
                return;
            } else if (op.trim().equals("addproduct")) {
                String name = request.getParameter("productName");
                int quantity = Integer.parseInt(request.getParameter("Quantity"));
                int price = Integer.parseInt(request.getParameter("price"));
                int discount = Integer.parseInt(request.getParameter("discount"));
                String description = request.getParameter("description");
                int catid = Integer.parseInt(request.getParameter("catid"));
                Part part = request.getPart("productPhoto");

                Product p = new Product();
                p.setPname(name);
                p.setQuantity(quantity);
                p.setPrice(price);
                p.setDiscount(discount);
                p.setDescription(description);
                p.setImage(part.getSubmittedFileName());
                CategoryDao cdao = new CategoryDao(Factoryprovider.getFactory());
                Category category = cdao.getCategoryById(catid);
                p.setCategory(category);
                ProductDao pdao = new ProductDao(Factoryprovider.getFactory());
                pdao.saveProduct(p);

                // Image upload
                String path = request.getServletContext().getRealPath("images") + File.separator + "products";
                File uploadDir = new File(path);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }
                FileOutputStream fos = new FileOutputStream(new File(uploadDir, part.getSubmittedFileName()));
                InputStream fis = part.getInputStream();
                byte[] data = new byte[fis.available()];
                fis.read(data);
                fos.write(data);
                fos.close();

                HttpSession session = request.getSession();
                session.setAttribute("message1", "Product Saved Successfully");
                response.sendRedirect("Admin.jsp");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
