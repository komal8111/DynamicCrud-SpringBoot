package com.demo.test.repo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.demo.entity.BookEntry;
import com.demo.entity.BookTransaction;
import com.demo.entity.NatureEnum;
import com.demo.repo.BookEntryRepo;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestBookEntryRepo {

	@Autowired
	private BookEntryRepo bookEntryRepo;
	
	private BookEntry bookEntryTestObj;
	
	@BeforeEach
	public void init()
	{
		bookEntryTestObj = new BookEntry();
		bookEntryTestObj.setCredit_amt(100);
		bookEntryTestObj.setDebit_amt(100);
		bookEntryTestObj.setGl_account("equity");
		bookEntryTestObj.setNature(NatureEnum.CREDIT);

	}

	@Test
	void save() {
		// arrange
		
		// act
		BookEntry newBookEntry = bookEntryRepo.save(bookEntryTestObj);

		// assert
		assertNotNull(newBookEntry);
		assertThat(newBookEntry.getBook_entry_id()).isNotEqualTo(null);

	}
	
	@Test
	@DisplayName("It should return the book Entry by id")
	public void getBookTransactionEntryById()
	{
		//arrange
		bookEntryRepo.save(bookEntryTestObj);
		
		//act
		BookEntry existingBookEntry=bookEntryRepo.findById(bookEntryTestObj.getBook_entry_id()).get();
		
		//assert
		assertNotNull(existingBookEntry);
		assertEquals("equity", existingBookEntry.getGl_account());
	
		
	}

	@Test
	@DisplayName("It should update the book Entry")
	public void updateBookEntry()
	{
		//arrange
		bookEntryRepo.save(bookEntryTestObj);
		
		//act
		BookEntry existingBookEntry=bookEntryRepo.findById(bookEntryTestObj.getBook_entry_id()).get();
		existingBookEntry.setCredit_amt(500);
		BookEntry newBookEntryObj=bookEntryRepo.save(existingBookEntry);
		
		//assert
		assertEquals(500, newBookEntryObj.getCredit_amt());
	}
	
	

}
