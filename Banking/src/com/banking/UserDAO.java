package com.banking;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserDAO {
	
	Scanner sc = new Scanner(System.in);
	private Connection con;

	public UserDAO() {
		super();
		con = DBConnection.getConnection();
	}

	public boolean registerchecking(User u) {
		try {

			String query = "insert into customer(id,name,password) values(?,?,?)";

			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, u.getId());
			ps.setString(2, u.getName());
			ps.setString(3, u.getPassword());

			int n = ps.executeUpdate();
			if (n == 0) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
	public void register() {
		System.out.print("Please Enter the ID : ");
		int id = sc.nextInt();
		System.out.print("Please Enter Name : ");
		String name = sc.next();
		System.out.print("Please Enter the Password : ");
		String password = sc.next();
		boolean status = false;
		String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";
		Pattern p = Pattern.compile(regex);
		if (password == null) {
			status = false;
		} else {

			Matcher m = p.matcher(password);
			status = m.matches();
		}
		if (status == true) {
			User u = new User(id, name, password);
			boolean s = registerchecking(u);
			if (s == true) {
				System.out.println("Customer Account was Registered!");
			} else {
				System.out.println("Please Try Again");
			}
		}
		else
		{
			System.err.println("Please follow the Password Criteria Minimum 8 Characters and one Special Character ");
		}
	}


	public boolean delete(int id, String password) {
		try {
			String query = "delete from customer where id=? AND password=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ps.setString(2, password);
			int n = ps.executeUpdate();
			if (n == 0) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			System.err.println(e);
			return false;
		}
	}

	public boolean search(int id) {
		try {
			String query = "select id from customer where id=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next() == true) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.err.println(e);
			return false;
		}
	}

	public boolean deposit(Deposit d) {
		try {
			String query = "insert into deposit(id,money) values(?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, d.getId());
			ps.setFloat(2, d.getMoney());
			int n = ps.executeUpdate();
			if (n == 0) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			System.err.println(e);
			return false;
		}
	}

	public boolean update(Deposit d) {
		try {
			String query = "update deposit set money=? where id=?";

			PreparedStatement ps;
			ps = con.prepareStatement(query);

			ps.setInt(2, d.getId());
			ps.setFloat(1, d.getMoney());

			int n = ps.executeUpdate();
			if (n == 0)
				return false;
			else
				return true;

		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
	}

	public float previous(int id) {
		try {
			String query = "select money from  deposit where id=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getFloat("money");
		} catch (Exception e) {
			System.err.println("User Account Balance Not Deposited Just Registered Account");
			return 0;
		}
	}
	
	public ArrayList<User> getAccountDetails(int id)
	{
		try
		{
			String query = "select id,name from customer where id=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			ArrayList<User> list = new ArrayList<>();
			while(rs.next()==true)
			{
				int id1 = rs.getInt(1);
				String name = rs.getString(2);
				User u = new User(id1, name);		
				u.setId(id);
				list.add(u);
			}
			
			return list;
		}
		catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	
}
