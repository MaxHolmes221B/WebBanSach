package com.MaxHolmes.WebBanSach.Model;

import java.sql.Date;

public class User{
	
    private int id;

    private String name;

    private String username;

    private String password;
    
    private Date dob;
    
    private String address;
    
    private String pnb;

    private String email;

    private String role;


    public User() {
        super();
    }

    

    public User(int id, String name, String username, String password, Date dob, String address, String pnb, String email,
			String role) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.dob = dob;
		this.address = address;
		this.pnb = pnb;
		this.email = email;
		this.role = role;
	}



	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPnb() {
        return pnb;
    }

    public void setPnb(String pnb) {
        this.pnb = pnb;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



	public Date getDob() {
		return dob;
	}



	public void setDob(Date dob) {
		this.dob = dob;
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", username=" + username + ", password=" + password + ", dob="
				+ dob + ", address=" + address + ", pnb=" + pnb + ", email=" + email + ", role=" + role + "]";
	}
    
	
}
