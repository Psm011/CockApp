package com.ecommerce.entities;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.ecommerce.entities.Category;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pid;
	@Column(columnDefinition = "TEXT")
	private String pname;
	@Column(length=2000)
	private int quantity;
	private int price;
	private int discount;
	@Lob
	@Column(columnDefinition = "TEXT")	
	private String description;
	private String image;
	@ManyToOne
	private  Category category;
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(int pid, String pname, int quantity, int price, int discount, String description,String image, Category category) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.quantity = quantity;
		this.price = price;
		this.discount = discount;
		this.description=description;
		this.image = image;
		this.category = category;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "Product [pid=" + pid + ", pname=" + pname + ", quantity=" + quantity + ", price=" + price
				+ ", discount=" + discount + ", description=" + description + ", image=" + image + ", category="
				+ category + "]";
	}
	
	//calculating actual prize of product by substracting discount
	public int getActualPrize() {
		int d=(int)((this.getDiscount()/100.0)*this.getPrice());
		return this.getPrice()-d;
	}
}
	