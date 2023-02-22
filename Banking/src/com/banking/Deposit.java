package com.banking;

public class Deposit 
{
	private int id;
	private float money;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getMoney() {
		return money;
	}
	public void setMoney(float money) {
		this.money = money;
	}
	public Deposit() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Deposit(int id, float money) {
		super();
		this.id = id;
		this.money = money;
	}
	@Override
	public String toString() {
		return "Deposit [id=" + id + ", money=" + money + "]";
	}
	
	
	
}
