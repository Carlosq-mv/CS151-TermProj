package application.models;

import java.time.LocalDate;

public class Account {
	private String accountName;
	private double  balance;
	private LocalDate date;
	
	public Account(String accountName, double balance, LocalDate date) {
		this.setAccountName(accountName);
		this.setBalance(balance);
		this.setOpeningDate(date);
	}
	
	public String getAccountName() {
		return accountName;
	}
	
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	public LocalDate getOpeningDate() {
		return date;
	}
	
	public void setOpeningDate(LocalDate openingDate) {
		this.date = openingDate;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public String toCSV() {
		return this.getAccountName() + "," + this.getBalance() + "," + this.getOpeningDate();
	}
}
