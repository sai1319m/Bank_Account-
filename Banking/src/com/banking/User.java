package com.banking;

public class User {
	private int id;
	private String name;
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User() {
		super();
	}
	public User(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public User(int id,String name, String password) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
	}

	@Override
	public String toString() {
		return "Account Type : Checking "+"\n"
				+ "UserId = " + id +"\n"
				+ "UserName=" + name;
	}
	

	
	

	
	

}
