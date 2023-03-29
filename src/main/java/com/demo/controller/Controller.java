package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.UpdateBookTransactionDto;
import com.demo.entity.BookTransaction;
import com.demo.service.BookEntryService;
import com.demo.service.BookTransactionService;

@RestController
public class Controller {
	@Autowired
	private BookTransactionService bookTransactionService;

	@Autowired
	BookEntryService bookEntryService;

	public Controller(BookTransactionService bookTransactionService, BookEntryService bookEntryService) {
		super();
		this.bookTransactionService = bookTransactionService;
		this.bookEntryService = bookEntryService;
	}

	@PutMapping("/updateData")
	public ResponseEntity<String> updateRecord(@RequestBody UpdateBookTransactionDto updateBookTransactionDto) {

		bookTransactionService.updateExistingRecord(updateBookTransactionDto);

		return ResponseEntity.ok("update record successfully");

	}

	@PostMapping("/addBookTransactionRecord")
	public ResponseEntity<String> AddBookTransactionEntry(
			@RequestBody UpdateBookTransactionDto updateBookTransactionDto) {

		boolean isCreditDebitEqual = bookEntryService.checkCreditDebitAmtIsEqual(updateBookTransactionDto);
		if (isCreditDebitEqual) {

			BookTransaction bookTransaction = new BookTransaction(updateBookTransactionDto.getLandlord_id(),
					updateBookTransactionDto.getBuilding_id(), updateBookTransactionDto.getUnit_id(),
					updateBookTransactionDto.getDate(), updateBookTransactionDto.getDescription(),
					updateBookTransactionDto.getBook_entry_name(), updateBookTransactionDto.getBookEntry());

			bookTransactionService.save(bookTransaction);
			return ResponseEntity.ok("save Transaction record successfully");
		} else {
			return ResponseEntity.ok("sorry dear...cant save your record....credit and debit amount should be equal");
		}

	}

	@GetMapping("/getDataById/{bookingId}")
	public ResponseEntity<BookTransaction> getRecordById(@PathVariable int bookingId) {
		BookTransaction bookTransaction = bookTransactionService.getSingleRecordById(bookingId);
		return ResponseEntity.ok(bookTransaction);

	}

}
