package com.perfios.bankaccount;

public class Block {
	private Account account;

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Block(Account account) {
		super();
		this.account = account;
	}

}
