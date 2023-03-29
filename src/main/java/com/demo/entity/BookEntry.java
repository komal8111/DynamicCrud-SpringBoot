package com.demo.entity;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@SQLDelete(sql = "UPDATE tbl_products SET deleted = true WHERE id=?")

public class BookEntry {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int book_entry_id;
	private String gl_account;
	
	@Enumerated(EnumType.STRING)
	private  NatureEnum nature ;
	
	private int credit_amt;
	private int debit_amt;
	
	@Column(columnDefinition = "boolean default false")
	private boolean deleted = Boolean.FALSE;
	
	public BookEntry() {
		super();
		// TODO Auto-generated constructor stu
	}

	

	public BookEntry(int book_entry_id, String gl_account, NatureEnum nature, int credit_amt, int debit_amt,
			boolean deleted) {
		super();
		this.book_entry_id = book_entry_id;
		this.gl_account = gl_account;
		this.nature = nature;
		this.credit_amt = credit_amt;
		this.debit_amt = debit_amt;
		this.deleted = deleted;
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
	
	

	public boolean isDeleted() {
		return deleted;
	}



	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}



	@Override
	public String toString() {
		return "BookEntry [book_entry_id=" + book_entry_id + ", gl_account=" + gl_account + ", nature=" + nature
				+ ", credit_amt=" + credit_amt + ", debit_amt=" + debit_amt + ", deleted=" + deleted + "]";
	}




}
