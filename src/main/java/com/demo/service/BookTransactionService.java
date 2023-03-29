package com.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dto.UpdateBookTransactionDto;
import com.demo.entity.BookEntry;
import com.demo.entity.BookTransaction;
import com.demo.entity.NatureEnum;
import com.demo.repo.BookEntryRepo;
import com.demo.repo.BookTransactionRepo;

@Service
public class BookTransactionService {

	@Autowired
	BookTransactionRepo bookTransactionRepo;

	@Autowired
	BookEntryRepo bookEntryRepo;

	public BookTransactionService(BookTransactionRepo bookTransactionRepo) {
		this.bookTransactionRepo = bookTransactionRepo;
	}

	public BookTransaction save(BookTransaction bookTransaction) {
		return bookTransactionRepo.save(bookTransaction);

	}

	public void updateExistingRecord(UpdateBookTransactionDto record) {

		Optional<BookTransaction> bookTransactionRecord = bookTransactionRepo.findById(record.getBook_transaction_id());

		// update existing Record
		if (bookTransactionRecord.isPresent()) {
			BookTransaction bookTransactionBackendRecord = bookTransactionRecord.get();
			bookTransactionBackendRecord.setLandlord_id(record.getLandlord_id());
			bookTransactionBackendRecord.setBuilding_id(record.getBuilding_id());
			bookTransactionBackendRecord.setUnit_id(record.getUnit_id());
			bookTransactionBackendRecord.setDate(record.getDate());
			bookTransactionBackendRecord.setDescription(record.getDescription());
			bookTransactionBackendRecord.setBook_entry_name(record.getBook_entry_name());

			List<BookEntry> bookEntriesBackendList = bookTransactionBackendRecord.getBookEntry();
			List<BookEntry> bookEntriesFrontendList = record.getBookEntry();

			List<BookEntry> BookEntryList = new ArrayList<>();

			for (BookEntry dbList : bookEntriesBackendList) {
				boolean found = false;
				
				for (BookEntry uiList : bookEntriesFrontendList) {
					if (dbList.getBook_entry_id() == uiList.getBook_entry_id()) {
						found = true;

						dbList.setGl_account(uiList.getGl_account());
						dbList.setNature(uiList.getNature());

						if (uiList.getNature() == NatureEnum.CREDIT) {

							dbList.setCredit_amt(uiList.getCredit_amt());
							dbList.setDebit_amt(0);
						}
						if (uiList.getNature() == NatureEnum.DEBIT) {
							dbList.setDebit_amt(uiList.getDebit_amt());
							dbList.setCredit_amt(0);
						}
					}
					// delete a record
					if (found == false) {
						System.out.println(dbList.getBook_entry_id() + " " + found + "not found");
						dbList.setDeleted(true);
						
					}

					

				}
			}
			// add subrecord into existing record
			for (BookEntry uiList : bookEntriesFrontendList) {
				boolean isNew = false;
				for (BookEntry dbList : bookEntriesBackendList) {
					if (uiList.getBook_entry_id() == 0) {
						isNew = true;
					}
				}
				if (isNew) {
					BookEntry bookEntry = new BookEntry();
					bookEntry.setCredit_amt(uiList.getCredit_amt());
					bookEntry.setDebit_amt(uiList.getDebit_amt());
					bookEntry.setGl_account(uiList.getGl_account());
					bookEntry.setNature(uiList.getNature());
					bookEntry.setDeleted(false);
					BookEntryList.add(bookEntry);
					isNew = false;
				}
			}

			bookEntriesBackendList.addAll(BookEntryList);
			bookTransactionRepo.save(bookTransactionBackendRecord);

		}

		else {
			throw new RuntimeException("Invalid booking id");
		}

	}

	//get single  record
	public BookTransaction getSingleRecordById(int bookTransactionId) {

		Optional<BookTransaction> bookTransaction = this.bookTransactionRepo.findById(bookTransactionId);
		if (bookTransaction.isPresent()) {
			BookTransaction bookTransactionRecord = bookTransaction.get();
			if (bookTransactionRecord.isDeleted())
				throw new RuntimeException("The record is inactive");
			else {
				List<BookEntry> bookEntriesBackendList = bookTransaction.get().getBookEntry();
				List<BookEntry> activeRecordList = new ArrayList<>();
				for (BookEntry list : bookEntriesBackendList) {
					if (list.isDeleted() == false)
						activeRecordList.add(list);
					// System.out.println(list+"--------------------------");
				}
				// System.out.println(activeRecordList);
				bookTransactionRecord.setBookEntry(activeRecordList);
				return bookTransactionRecord;
			}

		}
		// BookTransaction bookTrans=bookTransaction.get();

		return null;
	}

}
