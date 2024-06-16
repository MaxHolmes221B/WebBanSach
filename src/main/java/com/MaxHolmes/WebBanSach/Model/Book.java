package com.MaxHolmes.WebBanSach.Model;

import java.sql.Date;

public class Book {
	private int id;
	
	private String title;

	private String author;

	private Date date;

	private int nop;

	private String description;
	
	private int quantity_sold;

	private String pathImg;
	
	private int category_id;
	public Book() {
		super();
	}
	public Book(int id, String title, String author, Date date, int nop, String description, int quantity_sold,
			String pathImg, int category_id) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.date = date;
		this.nop = nop;
		this.description = description;
		this.quantity_sold = quantity_sold;
		this.pathImg = pathImg;
		this.category_id = category_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getNop() {
		return nop;
	}
	public void setNop(int nop) {
		this.nop = nop;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getQuantity_sold() {
		return quantity_sold;
	}
	public void setQuantity_sold(int quantity_sold) {
		this.quantity_sold = quantity_sold;
	}
	public String getPathImg() {
		return pathImg;
	}
	public void setPathImg(String path_img) {
		this.pathImg = path_img;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	
}
