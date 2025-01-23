package com.ecommerce.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;
@Entity
public class Orders {

	
	@TableGenerator(name = "orders")
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    private String email;
	    private String name;
	    private String mobile;
	    private String address;
	    private String paymentStatus;
	    
		public Orders() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Orders(Long id, String email, String name, String mobile, String address, String paymentStatus) {
			super();
			this.id = id;
			this.email = email;
			this.name = name;
			this.mobile = mobile;
			this.address = address;
			this.paymentStatus = paymentStatus;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
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

	    
	    // Constructors, getters, and setters
	}


