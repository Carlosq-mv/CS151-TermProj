package application.models;

import java.time.LocalDate;

public class Transaction {
	private String accountName;
	private String transactionType;
	private LocalDate date;
	private String description;
	private double paymentAmonunt;
	private double depositAmount;
	
	public Transaction(String accountName, String transactionType, LocalDate date, String description) {
		this.accountName = accountName;
		this.transactionType = transactionType;
		this.date = date;
		this.description = description;
		this.paymentAmonunt = 0.0;
		this.depositAmount = 0.0;
	}
	
	public String getAccountName() {
		return accountName;
	}
	
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	public String getTransactionType() {
		return transactionType;
	}
	
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public double getPaymentAmonunt() {
		return paymentAmonunt;
	}
	
	public void setPaymentAmonunt(double paymentAmonunt) {
		this.paymentAmonunt = paymentAmonunt;
	}
	
	public double getDepositAmount() {
		return depositAmount;
	}
	
	public void setDepositAmount(double depositAmount) {
		this.depositAmount = depositAmount;
	}
	
}
