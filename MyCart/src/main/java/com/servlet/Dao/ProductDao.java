package com.servlet.Dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.ecommerce.entities.Product;

public class ProductDao {
    private SessionFactory factory;

    public ProductDao(SessionFactory factory) {
        super();
        this.factory = factory;
    }

    public boolean saveProduct(Product product) {
        boolean f = false;
        try {
            Session session = this.factory.openSession();
            Transaction t = session.beginTransaction();
            session.save(product);
            t.commit();
            session.close();
            f = true;
        } catch (Exception e) {
            e.printStackTrace();
            f = false;
        }
        return f;
    }

    // Getting all products
    public List<Product> getAllProducts() {
        Session session = this.factory.openSession();
        Query<Product> q = session.createQuery("from Product", Product.class);
        List<Product> list = q.list();
        session.close();
        return list;
    }

    // Getting all products by category id
    public List<Product> getAllProductsByCategoryId(int cid) {
        Session session = this.factory.openSession();
        Query<Product> q = session.createQuery("from Product as p where p.category.categoryid=:id", Product.class);
        q.setParameter("id", cid);
        List<Product> list = q.list();
        session.close();
        return list;
    }

    // Getting product by ID
    public Product getProductById(int productId) {
        Session session = this.factory.openSession();
        Product product = session.get(Product.class, productId);
        session.close();
        return product;
    }

    public void removeProduct(int productId) {
        Session session = this.factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Product product = session.get(Product.class, productId);
            if (product != null) {
                session.delete(product);
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
