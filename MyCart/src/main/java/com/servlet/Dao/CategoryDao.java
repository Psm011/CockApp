package com.servlet.Dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.ecommerce.entities.Category;
import com.ecommerce.entities.Product;

public class CategoryDao {
    private SessionFactory factory;

    public CategoryDao(SessionFactory factory) {
        super();
        this.factory = factory;
    }

    // Save the category to the database
    public int saveCategory(Category cat) {
        Session session = this.factory.openSession();
        Transaction t = session.beginTransaction();
        int catid = (int) session.save(cat);
        t.commit();
        session.close();
        return catid;
    }

    public List<Category> getCategories() {
        Session s = this.factory.openSession();
        Query q = s.createQuery("from Category");
        List<Category> list = q.list();
        s.close(); // Close the session after use
        return list;
    }

    public Category getCategoryById(int categoryId) {
        Category cat = null;
        try {
            Session session = this.factory.openSession();
            cat = session.get(Category.class, categoryId);
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cat;
    }

    public void removeCategory(int categoryId) {
        Session session = this.factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Category category = session.get(Category.class, categoryId);
            if (category != null) {
                // Remove or reassign products associated with this category
                Query<Product> productQuery = session.createQuery("from Product where category.categoryid = :categoryId", Product.class);
                productQuery.setParameter("categoryId", categoryId);
                List<Product> products = productQuery.list();

                for (Product product : products) {
                    session.delete(product); // Or reassign product.setCategory(null);
                }

                session.delete(category);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
