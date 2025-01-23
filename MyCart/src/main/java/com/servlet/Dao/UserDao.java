package com.servlet.Dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.ecommerce.entities.User;

public class UserDao {
	private SessionFactory factory;

	public UserDao(SessionFactory factory) {
		super();
		this.factory = factory;
	}
	//get user by email and password
	
	public User getUserByEmailAndPassword(String mail, String Password) {
		User user=null;
		try {
			String query="from User where email=:e and password=:p";
			Session session=this.factory.openSession();
			Query q=session.createQuery(query);
			q.setParameter("e", mail);
			q.setParameter("p",Password);
			user=(User) q.uniqueResult();
			session.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	public List<User> getAllUsers() {
        Session session = this.factory.openSession();
        Query<User> query = session.createQuery("from User", User.class);
        List<User> users = query.list();
        session.close();
        return users;
    }
	
	 public void removeUser(int userId) {
	        Session session = this.factory.openSession();
	        session.beginTransaction();
	        User user = session.get(User.class, userId);
	        if (user != null) {
	            session.delete(user);
	        }
	        session.getTransaction().commit();
	        session.close();

}
}
