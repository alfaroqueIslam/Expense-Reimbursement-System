package dev.islam.models;

public class Request {
	

	protected double amount = 1000;
	protected String status = "approved";
	protected int requestId = 7;
	protected String email = "test@gmail.com";
	protected String date;
	protected String reason;
	protected int userId;

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int accountId) {
		this.requestId = accountId;
	}

	public String getStatus() {
		return status;
	}

	public Request() {
		// TODO Auto-generated constructor stub
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double balance) {
		this.amount = balance;
	}

	public void setStatus(String approved) {
		this.status = approved;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
