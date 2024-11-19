package application.models;

import java.time.LocalDate;

public class ScheduledTransaction {
	
	private String name;
	private String account;
	private String transaction_type;
	private String frequency;
	private LocalDate due_date;
	private double pay_amount;

	public ScheduledTransaction(String name, String account, String transaction_type, String frequency, LocalDate due_date, double pay_amount) {
		this.name = name;
		this.account = account;
		this.transaction_type = transaction_type;
		this.frequency = frequency;
		this.due_date = due_date;
		this.pay_amount = pay_amount;
	}

	public String getName() {
		return this.name;
	}	

	public String getAccount() {
		return this.account;
	}

	public String getTransactionType() {
		return this.transaction_type;
	}

	public String getFrequency() {
		return this.frequency;
	}

	public LocalDate getDueDate() {
		return this.due_date;
	}

	public double getPayAmount() {
		return this.pay_amount;
	}

	public void setName(String name) {
		this.name = name;
	}	

	public void setAccount(String account) {
		this.account = account;
	}

	public void setTransactionType(String transaction_type) {
		this.transaction_type = transaction_type;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public void setDueDate(LocalDate due_date) {
		this.due_date = due_date;
	}

	public void setPayAmount(double pay_amount) {
		this.pay_amount = pay_amount;
	}
	
	
}
