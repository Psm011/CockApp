package com.servlet.Dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ecommerce.entities.Order;
import com.ecommerce.entities.User;

public class OrderDao {
    private SessionFactory factory;

    public OrderDao(SessionFactory factory) {
        this.factory = factory;
    }

    public void saveOrder(Order order) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            session.save(order);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public Order getOrderByUser(User user) {
        Order order = null;
        try (Session session = factory.openSession()) {
            String hql = "FROM Order WHERE user = :user AND paymentStatus = 'Incomplete'";
            order = session.createQuery(hql, Order.class)
                           .setParameter("user", user)
                           .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return order;
    }

    public void updateOrder(Order order) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            session.update(order);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
}
