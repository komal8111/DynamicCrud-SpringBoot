package com.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dto.UpdateBookTransactionDto;
import com.demo.entity.BookEntry;
import com.demo.entity.BookTransaction;
import com.demo.entity.NatureEnum;
import com.demo.repo.BookEntryRepo;
import com.demo.repo.BookTransactionRepo;

import jakarta.persistence.Column;

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

			boolean found = false;
			int bookEntriesCount = 0;
			List<BookEntry> BookEntryList = new ArrayList<>();

			boolean addAllNewRecords = false;
			for (BookEntry dbList : bookEntriesBackendList) {
				found = false;

				for (BookEntry uiList : bookEntriesFrontendList) {

					// update Existing book Entry Record
					if (dbList.getBook_entry_id() == uiList.getBook_entry_id()) {
						found = true;
						bookEntriesCount++;
						UpdateExistingBookEntryRecord(dbList, uiList);

					}

					// add to existing record
					if (uiList.getBook_entry_id() == 0 && addAllNewRecords == false) {
						AddNewBookEntryToExistingBookTransaction(uiList, BookEntryList);
						addAllNewRecords = true;
						bookEntriesCount++;
					}

				}
				// delete
				CheckIsAnyTransactionEntryDelete(found, dbList, bookEntriesCount, bookTransactionBackendRecord);

			}

			bookEntriesBackendList.addAll(BookEntryList);
			bookTransactionRepo.save(bookTransactionBackendRecord);

		}

		else {
			throw new RuntimeException("Invalid booking id");
		}

	}

	private void UpdateExistingBookEntryRecord(BookEntry dbList, BookEntry uiList) {
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

	private void AddNewBookEntryToExistingBookTransaction(BookEntry uiList, List<BookEntry> BookEntryList) {

		BookEntry bookEntry = new BookEntry();
		bookEntry.setCredit_amt(uiList.getCredit_amt());
		bookEntry.setDebit_amt(uiList.getDebit_amt());
		bookEntry.setGl_account(uiList.getGl_account());
		bookEntry.setNature(uiList.getNature());
		bookEntry.setDeleted(false);
		BookEntryList.add(bookEntry);
	}

	private void CheckIsAnyTransactionEntryDelete(boolean found, BookEntry dbList, int bookEntriesCount,
			BookTransaction bookTransactionBackendRecord) {
		if (found == false) {
			dbList.setDeleted(true);

			if (bookEntriesCount == 0) {
				bookTransactionBackendRecord.setDeleted(true);
			}
		}

	}

	// get single record
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

		return null;
	}

}
