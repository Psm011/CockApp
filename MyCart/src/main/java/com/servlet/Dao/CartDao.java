package com.servlet.Dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import com.ecommerce.entities.Cart;

public class CartDao {
    private SessionFactory factory;

    public CartDao(SessionFactory factory) {
        this.factory = factory;
    }

    public Cart getCartByProductIdAndUserEmail(int productId, String userEmail) {
        Session session = this.factory.openSession();
        String hql = "FROM Cart c WHERE c.productId = :productId AND c.userEmail = :userEmail";
        Query<Cart> query = session.createQuery(hql, Cart.class);
        query.setParameter("productId", productId);
        query.setParameter("userEmail", userEmail);
        Cart cartItem = query.uniqueResult();
        session.close();
        return cartItem;
    }
    public List<Cart> getAllCartItems() {
        List<Cart> cartItems = null;
        Session session = this.factory.openSession();
        try {
            String query = "FROM Cart";
            Query<Cart> q = session.createQuery(query);
            cartItems = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return cartItems;
    }

    public List<Cart> getCartItemsByUserEmail(String userEmail) {
        Session session = this.factory.openSession();
        String hql = "FROM Cart c WHERE c.userEmail = :userEmail";
        Query<Cart> query = session.createQuery(hql, Cart.class);
        query.setParameter("userEmail", userEmail);
        List<Cart> cartItems = query.list();
        session.close();
        return cartItems;
    }

    public void saveOrUpdateCart(Cart cartItem) {
        Session session = this.factory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(cartItem);
        session.getTransaction().commit();
        session.close();
    }
    public void removeCartById(int cartId) {
        Session session = this.factory.openSession();
        session.beginTransaction();
        Cart cart = session.get(Cart.class, cartId);
        if (cart != null) {
            session.delete(cart);
        }
        session.getTransaction().commit();
        session.close();
    }

	
}
