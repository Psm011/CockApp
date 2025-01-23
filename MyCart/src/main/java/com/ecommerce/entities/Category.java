package com.ecommerce.entities;
import java.util.ArrayList;


import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.ecommerce.entities.Product;

import javax.persistence.Lob;
@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int categoryid;
	@Column(length = 500)
	private String categoryTitle;
	 @Lob
	 @Column(columnDefinition = "TEXT")
	private String CategoryDescription;
	@OneToMany(mappedBy = "category")
	private List<Product>product=new ArrayList();
	

	public Category( String categoryTitle, String categoryDescription,List<Product>product ) {
		super();
		this.categoryTitle = categoryTitle;
		CategoryDescription = categoryDescription;
		this.product=product;
	}

	

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}

	public String getCategoryTitle() {
		return categoryTitle;
	}

	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}

	public String getCategoryDescription() {
		return CategoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		CategoryDescription = categoryDescription;
	}
	

	public List<Product> getProduct() {
		return product;
	}



	public void setProduct(List<Product> product) {
		this.product = product;
	}



	@Override
	public String toString() {
		return "Category [categoryid=" + categoryid + ", categoryTitle=" + categoryTitle + ", CategoryDescription="
				+ CategoryDescription + "]";
	}

}
