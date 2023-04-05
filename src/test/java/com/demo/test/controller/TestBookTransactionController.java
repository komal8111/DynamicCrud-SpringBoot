package com.demo.test.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.demo.controller.BookingTransactionController;
import com.demo.entity.BookEntry;
import com.demo.entity.BookTransaction;
import com.demo.entity.NatureEnum;
import com.demo.service.BookEntryService;
import com.demo.service.BookTransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(BookingTransactionController.class)
class TestBookTransactionController {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BookTransactionService bookTransactionService;

	@MockBean
	private BookEntryService bookEntryService;

	private BookTransaction bookTransactionTestObj;

	@Test
	public void testAddBookTransactionEntry() throws Exception {
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

		when(bookTransactionService.save(bookTransactionTestObj)).thenReturn(bookTransactionTestObj);

		mockMvc.perform(post("/addBookTransactionRecord").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(bookTransactionTestObj))).andExpect(status().isOk());

	}

}
