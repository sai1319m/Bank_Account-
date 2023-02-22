package com.banking;

import java.util.ArrayList;
import java.util.Scanner;

public class BankHomeScreen {
	public static void main(String[] args) {
//		UserScreen uscreen = new UserScreen();
		UserDAO udao = new UserDAO();
		Scanner sc = new Scanner(System.in);
		int N, id;
		String password;
		boolean st;
		Deposit dep = null;
		do {
			System.out.println("1. Create An Account");
			System.out.println("2. Delete An Account");
			System.out.println("3. Make An Account Deposit");
			System.out.println("4. Add Money");
			System.out.println("5. Make An Account Withdrawal");
			System.out.println("6. Check An Account Balance");
			System.out.println("7. Exit");
			System.out.print("Please Enter Your Choice : ");
			N = sc.nextInt();

			switch (N) {
			case 1:
				udao.register();
				break;
			case 2:
				System.out.print("Please Enter the UserId : ");
				id = sc.nextInt();
				System.out.println("Please Enter the Password : ");
				password = sc.next();
				boolean s = udao.delete(id, password);
				if (s == true) {
					System.out.println("The Customer Account was Deleted Successfully!");
				} else {
					System.err.println("Customer Account doesn't Exists!");
					System.err.println("Please Enter the Correct Details");
				}
				break;
			case 3:
				System.out.print("Please Enter the UserId : ");
				id = sc.nextInt();
				st = udao.search(id);
				if (st == true) {
					System.out.print("Please Enter the Amount : ");
					float money = sc.nextFloat();
					if (money > 0) {
						dep = new Deposit(id, money);
						boolean st1 = udao.deposit(dep);
						if (st1 == true) {
							System.out.println("Money Successfully Deposited!");
						} else {
							System.err.println("Money Doesn't Deposit Successfully!");
						}
					} else {
						System.err.println("Please Enter Amount Value Positive Only!");
					}
				} else {
					System.err.println("Customer UserId Doesn't Found!");
				}
				break;
			case 4:
				System.out.print("Please Enter the UserId : ");
				id = sc.nextInt();
				st = udao.search(id);
				if (st == true) {
					System.out.print("Please Enter the Amount : ");
					float money = sc.nextFloat();
					if(money>0)
					{
						float previous = udao.previous(id);
						System.out.println(previous + money);
						float total = previous + money;
						if (total > 0) {
							dep = new Deposit(id, total);
							boolean st2 = udao.update(dep);
							if (st2 == true) {
								System.out.println("Money Added Successfully");
							} else {
								System.err.println("Money Doesn't Added Successfully!");
							}
						}
					}
					else
					{
						System.err.println("Please Enter Amount only in Positive Values");
					}
				}
					else {
					}
					System.err.println("Customer UserId Doesn't Found!");

				break;
			case 5:
				System.out.print("Please Enter the UserId : ");
				id = sc.nextInt();
				st = udao.search(id);
				if (st == true) {
					System.out.print("Please Enter the Amount : ");
					float money = sc.nextFloat();
					if(money>0)
					{
						float previous = udao.previous(id);
						if (previous > money) {
							float total = previous - money;
							dep = new Deposit(id, total);
							boolean st2 = udao.update(dep);
							if (st2 == true) {
								System.out.println("Money Withdraw Successfully " +money);
							} else {
								System.err.println("Money Doesn't Withdraw Successfully!");
							}
						}
						else
						{
							System.err.println("Enough Funds!");
						}
					}
					else
					{
						System.err.println("Please Enter Amount only in Positive Values");
					}
				
				}
					else {
						System.err.println("Customer UserId Doesn't Found!");
					}

				break;
				
			case 6:
				System.out.print("Please Enter the UserId : ");
				id = sc.nextInt();
				st = udao.search(id);
				if(st==true)
				{
					ArrayList<User> list = udao.getAccountDetails(id);
					if(list.isEmpty()==true)
					{
						System.err.println("Customer Details Not Found!");
					}
					else
					{
						
						for(User t : list)
						{
							System.out.println(t);
						}
					}
				}
				else
				{
					System.err.println("Customer Details Doesn't Exists!");
				}
				float balance=udao.previous(id);
				if(balance>0)
				{	
					System.out.println("Account Balance : "+balance);
				}
				break;
			case 7:
				System.out.println("Thanks For Choosing our Bank");
				break;
			default:
				System.err.println("Please enter a valid choice (1 thru 6)");
				break;
			}
		} while (N != 6);
		sc.close();
	}
}
