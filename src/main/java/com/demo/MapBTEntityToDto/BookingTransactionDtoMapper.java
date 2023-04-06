package com.demo.MapBTEntityToDto;

import java.util.ArrayList;
import java.util.List;

import com.demo.dto.UpdateBookTransactionDto;
import com.demo.entity.BookEntry;
import com.demo.entity.BookTransaction;
import com.demo.entity.NatureEnum;

public class BookingTransactionDtoMapper {

	// convert BookTransaction JpaEntity to BookTransactionDto
	public static UpdateBookTransactionDto mapToBookTransactionDto(BookTransaction bookTransaction) {
		List<BookEntry> bookEntryList = new ArrayList<>();
		BookEntry firstRecord = new BookEntry();
		firstRecord.setBook_entry_id(1);
		firstRecord.setCredit_amt(100);
		firstRecord.setDebit_amt(0);
		firstRecord.setDeleted(false);
		firstRecord.setGl_account("equity");
		firstRecord.setNature(NatureEnum.CREDIT);

		bookEntryList.add(firstRecord);

		BookEntry secondRecord = new BookEntry();
		secondRecord.setBook_entry_id(2);
		secondRecord.setCredit_amt(0);
		secondRecord.setDebit_amt(100);
		secondRecord.setDeleted(false);
		secondRecord.setGl_account("equity");
		secondRecord.setNature(NatureEnum.DEBIT);

		bookEntryList.add(secondRecord);
		UpdateBookTransactionDto bookTransactionDto = new UpdateBookTransactionDto();
		bookTransactionDto.setBook_transaction_id(1);
		bookTransactionDto.setBook_entry_name("equity");
		bookTransactionDto.setBuilding_id(20);
		bookTransactionDto.setDate(null);
		bookTransactionDto.setDescription("This is equity");
		bookTransactionDto.setLandlord_id(130);
		bookTransactionDto.setUnit_id(6);
		bookTransactionDto.setBookEntry(bookEntryList);

		return bookTransactionDto;

	}

	
	//convert StudentDto into student Jpa Entity
		public static  BookTransaction mapBookTransaction(UpdateBookTransactionDto updateBookTransactionDto)
		{
			
			List<BookEntry> bookEntryList = new ArrayList<>();
			BookEntry firstRecord = new BookEntry();
			firstRecord.setBook_entry_id(1);
			firstRecord.setCredit_amt(100);
			firstRecord.setDebit_amt(0);
			firstRecord.setDeleted(false);
			firstRecord.setGl_account("equity");
			firstRecord.setNature(NatureEnum.CREDIT);

			bookEntryList.add(firstRecord);

			BookEntry secondRecord = new BookEntry();
			secondRecord.setBook_entry_id(2);
			secondRecord.setCredit_amt(0);
			secondRecord.setDebit_amt(100);
			secondRecord.setDeleted(false);
			secondRecord.setGl_account("equity");
			secondRecord.setNature(NatureEnum.DEBIT);

			bookEntryList.add(secondRecord);
			
			BookTransaction bookTransactionObj = new BookTransaction();
			bookTransactionObj.setBook_transaction_id(1);
			bookTransactionObj.setBook_entry_name("equity");
			bookTransactionObj.setBuilding_id(20);
			bookTransactionObj.setDate(null);
			bookTransactionObj.setDescription("This is equity");
			bookTransactionObj.setLandlord_id(130);
			bookTransactionObj.setUnit_id(6);
			bookTransactionObj.setBookEntry(bookEntryList);
			
			return bookTransactionObj;

			
		}
	
	
}
