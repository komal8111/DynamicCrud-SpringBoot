package com.demo.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class BookTransaction {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int book_transaction_id;
	private int landlord_id;
	private int building_id;
	private int unit_id;
	
	
	private Date date;
	private String description;
	private String book_entry_name;
	
	@Column(columnDefinition = "boolean default false")
	private boolean deleted = Boolean.FALSE;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,targetEntity = BookEntry.class)
	//Eager Loading is a design pattern in which data initialization occurs on the spot.
	@JoinColumn(name = "book_transaction_id", referencedColumnName = "book_transaction_id")
	private List<BookEntry> bookEntry;

	
	
	public BookTransaction() {
		super();
		// TODO Auto-generated constructor stub
	}



	public BookTransaction( int landlord_id, int building_id, int unit_id, Date date,
			String description, String book_entry_name, List<BookEntry> bookEntry) {
		super();
	
		this.landlord_id = landlord_id;
		this.building_id = building_id;
		this.unit_id = unit_id;
		this.date = date;
		this.description = description;
		this.book_entry_name = book_entry_name;
		this.bookEntry = bookEntry;
	}



	public int getBook_transaction_id() {
		return book_transaction_id;
	}



	public void setBook_transaction_id(int book_transaction_id) {
		this.book_transaction_id = book_transaction_id;
	}



	public int getLandlord_id() {
		return landlord_id;
	}



	public void setLandlord_id(int landlord_id) {
		this.landlord_id = landlord_id;
	}



	public int getBuilding_id() {
		return building_id;
	}



	public void setBuilding_id(int building_id) {
		this.building_id = building_id;
	}



	public int getUnit_id() {
		return unit_id;
	}



	public void setUnit_id(int unit_id) {
		this.unit_id = unit_id;
	}



	public Date getDate() {
		Date d=new Date();
		return d;
	}



	public void setDate(Date date) {
		Date d=new Date();
		this.date = d;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getBook_entry_name() {
		return book_entry_name;
	}



	public void setBook_entry_name(String book_entry_name) {
		this.book_entry_name = book_entry_name;
	}



	public List<BookEntry> getBookEntry() {
		return bookEntry;
	}



	public void setBookEntry(List<BookEntry> bookEntry) {
		this.bookEntry = bookEntry;
	}



	public boolean isDeleted() {
		return deleted;
	}



	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}



	@Override
	public String toString() {
		return "BookTransaction [book_transaction_id=" + book_transaction_id + ", landlord_id=" + landlord_id
				+ ", building_id=" + building_id + ", unit_id=" + unit_id + ", date=" + date + ", description="
				+ description + ", book_entry_name=" + book_entry_name + ", deleted=" + deleted + ", bookEntry="
				+ bookEntry + "]";
	}







	


	
	
	
	
	
	

}
