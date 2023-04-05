package com.demo.test.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.demo.entity.BookEntry;
import com.demo.entity.BookTransaction;
import com.demo.entity.NatureEnum;
import com.demo.repo.BookEntryRepo;
import com.demo.repo.BookTransactionRepo;
import com.demo.service.BookTransactionService;

@SpringBootTest
class BookingTransactionServiceTest {

	@Autowired
	private BookTransactionService bookTransactionService;

	@MockBean
	private BookTransactionRepo bookTransactionRepo;

	@MockBean
	private BookEntryRepo bookEntryRepo;

	private BookTransaction bookTransactionTestObj;

	@BeforeEach
	public void init() {
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

		bookTransactionTestObj = new BookTransaction();
		bookTransactionTestObj.setBook_transaction_id(1);
		bookTransactionTestObj.setBook_entry_name("equity");
		bookTransactionTestObj.setBuilding_id(20);
		bookTransactionTestObj.setDate(null);
		bookTransactionTestObj.setDescription("This is equity");
		bookTransactionTestObj.setLandlord_id(130);
		bookTransactionTestObj.setUnit_id(6);
		bookTransactionTestObj.setBookEntry(bookEntryList);

	}

	@Test
	@DisplayName("Should save the book transaction object to the database")
	void save() {

		// act
		when(bookTransactionRepo.save(any(BookTransaction.class))).thenReturn(bookTransactionTestObj);
		BookTransaction newBookTransaction = bookTransactionRepo.save(bookTransactionTestObj);
		System.out.println(newBookTransaction + "-----------------------------");

		// assert
		assertNotNull(newBookTransaction);
		assertThat(newBookTransaction.getBookEntry().size()).isEqualTo(2);
		assertThat(newBookTransaction).isNotNull();
		
	}

	

}
