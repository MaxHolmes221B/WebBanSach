package com.MaxHolmes.WebBanSach.Model;

public class Comment {
	private Long id;
	private String content;
	private Long user_id;
	private Long book_id;
	public Comment() {
		super();
	}
	public Comment(Long id, String content, Long user_id, Long book_id) {
		super();
		this.id = id;
		this.content = content;
		this.user_id = user_id;
		this.book_id = book_id;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public Long getBook_id() {
		return book_id;
	}
	public void setBook_id(Long book_id) {
		this.book_id = book_id;
	}
	
	
}
