package com.demo.dto;

import com.demo.entity.NatureEnum;

public class AddTransactionEntryDto {
	

	int book_transaction_id;
	private int book_entry_id;
	private String gl_account;
	private  NatureEnum nature ;
	private int credit_amt;
	private int debit_amt;
	
	
	
	public AddTransactionEntryDto() {
		super();
		// TODO Auto-generated constructor stub
	}



	public AddTransactionEntryDto( int book_entry_id, String gl_account, NatureEnum nature,
			int credit_amt, int debit_amt) {
		super();
		this.book_transaction_id = book_transaction_id;
		this.book_entry_id = book_entry_id;
		this.gl_account = gl_account;
		this.nature = nature;
		this.credit_amt = credit_amt;
		this.debit_amt = debit_amt;
	}



	public int getBook_transaction_id() {
		return book_transaction_id;
	}



	public void setBook_transaction_id(int book_transaction_id) {
		this.book_transaction_id = book_transaction_id;
	}



	public int getBook_entry_id() {
		return book_entry_id;
	}



	public void setBook_entry_id(int book_entry_id) {
		this.book_entry_id = book_entry_id;
	}



	public String getGl_account() {
		return gl_account;
	}



	public void setGl_account(String gl_account) {
		this.gl_account = gl_account;
	}



	public NatureEnum getNature() {
		return nature;
	}



	public void setNature(NatureEnum nature) {
		this.nature = nature;
	}



	public int getCredit_amt() {
		return credit_amt;
	}



	public void setCredit_amt(int credit_amt) {
		this.credit_amt = credit_amt;
	}



	public int getDebit_amt() {
		return debit_amt;
	}



	public void setDebit_amt(int debit_amt) {
		this.debit_amt = debit_amt;
	}



	@Override
	public String toString() {
		return "AddTransactionEntryDto [book_transaction_id=" + book_transaction_id + ", book_entry_id=" + book_entry_id
				+ ", gl_account=" + gl_account + ", nature=" + nature + ", credit_amt=" + credit_amt + ", debit_amt="
				+ debit_amt + "]";
	}
	
	
}
