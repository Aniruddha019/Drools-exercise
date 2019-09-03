package com.perfios.bankaccount;

public class Transaction {
	
	private int accountNo;
	private TransactionType transactionType;
	private float amount;
	
	public TransactionType getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public int getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}
	public Transaction(int accountNo, TransactionType transactionType, float amount) {
		super();
		this.accountNo = accountNo;
		this.transactionType = transactionType;
		this.amount = amount;
	}
	
}
