package com.demo.test.repo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.demo.entity.BookEntry;
import com.demo.entity.BookTransaction;
import com.demo.entity.NatureEnum;
import com.demo.repo.BookTransactionRepo;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TestBookTransactionRepo {
	
	@Autowired
	BookTransactionRepo bookTransactionRepo;
	
	private BookTransaction bookTransactionTestObj;
	
	@BeforeEach
	public void init()
	{
		bookTransactionTestObj=new BookTransaction();
		bookTransactionTestObj.setBook_entry_name("equity");
		bookTransactionTestObj.setBuilding_id(20);
		bookTransactionTestObj.setDate(null);
		bookTransactionTestObj.setDescription("This is equity");
		bookTransactionTestObj.setLandlord_id(130);
		bookTransactionTestObj.setUnit_id(6);
		
		
	}

	@Test
	public void save() {
		//arrange
		
		//act
		BookTransaction newBookTransaction=bookTransactionRepo.save(bookTransactionTestObj);
		
		//assert
		assertNotNull(newBookTransaction);
		assertThat(newBookTransaction.getBook_transaction_id()).isNotEqualTo(null);
	}
	
	
	@Test
	@DisplayName("It should return the book Transaction Entry by id")
	public void getBookTransactionById()
	{
		//arrange
		bookTransactionRepo.save(bookTransactionTestObj);
		
		//act
		BookTransaction existingBookTransaction=bookTransactionRepo.findById(bookTransactionTestObj.getBook_transaction_id()).get();
		
		//assert
		assertNotNull(existingBookTransaction);
		assertEquals("This is equity", existingBookTransaction.getDescription());
		assertThat(existingBookTransaction.getLandlord_id()).isBetween(1, 1000);
		
	}
	
	@Test
	@DisplayName("It should update the book Transaction Entry")
	public void updateBookTransaction()
	{
		//arrange	
		bookTransactionRepo.save(bookTransactionTestObj);
		
		//act
		BookTransaction existingBookTransaction=bookTransactionRepo.findById(bookTransactionTestObj.getBook_transaction_id()).get();
		
		existingBookTransaction.setBook_entry_name("asset");
		

		BookTransaction newBookTransactionObj=bookTransactionRepo.save(existingBookTransaction);
		
		//assert
		assertEquals("asset", newBookTransactionObj.getBook_entry_name());
		assertEquals("This is equity", newBookTransactionObj.getDescription());

	}
	


}
