package com.demo.dto;

import java.time.LocalDate;
import java.util.List;

import com.demo.entity.BookEntry;

public class UpdateBookTransactionDto {
	
	private int book_transaction_id;
	private int landlord_id;
	private int building_id;
	private int unit_id;
	private LocalDate date;
	private String description;
	private String book_entry_name;
	
	
	private List<BookEntry> bookEntry;

	

	public UpdateBookTransactionDto() {
		super();
		// TODO Auto-generated constructor stub
	}



	public UpdateBookTransactionDto(int book_transaction_id, int landlord_id, int building_id, int unit_id,
			LocalDate date, String description, String book_entry_name, List<BookEntry> bookEntry) {
		super();
		this.book_transaction_id = book_transaction_id;
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



	@Override
	public String toString() {
		return "UpdateBookTransactionDto [book_transaction_id=" + book_transaction_id + ", landlord_id=" + landlord_id
				+ ", building_id=" + building_id + ", unit_id=" + unit_id + ", date=" + date + ", description="
				+ description + ", book_entry_name=" + book_entry_name + ", bookEntry=" + bookEntry + "]";
	}
	
	
}
