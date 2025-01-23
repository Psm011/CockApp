package com.ecommerce.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String FirstName;
    private String LastName;
    private String email;
    private String mobile;
    private String address;
    private String paymentStatus;

    @ManyToOne
    private User user;

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(int id, String firstName, String lastName, String email, String mobile, String address,
			String paymentStatus, User user) {
		super();
		this.id = id;
		FirstName = firstName;
		LastName = lastName;
		this.email = email;
		this.mobile = mobile;
		this.address = address;
		this.paymentStatus = paymentStatus;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", FirstName=" + FirstName + ", LastName=" + LastName + ", email=" + email
				+ ", mobile=" + mobile + ", address=" + address + ", paymentStatus=" + paymentStatus + ", user=" + user
				+ "]";
	}

}