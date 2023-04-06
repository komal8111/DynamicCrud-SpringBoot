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

		BookTransaction bookTransactionRecord = getSingleRecordById(record.getBook_transaction_id());

		// update existing Record
		if (bookTransactionRecord != null) {
			BookTransaction bookTransactionBackendRecord = bookTransactionRecord;
			bookTransactionBackendRecord.setLandlord_id(record.getLandlord_id());
			bookTransactionBackendRecord.setBuilding_id(record.getBuilding_id());
			bookTransactionBackendRecord.setUnit_id(record.getUnit_id());
			bookTransactionBackendRecord.setDate(record.getDate());
			bookTransactionBackendRecord.setDescription(record.getDescription());
			bookTransactionBackendRecord.setBook_entry_name(record.getBook_entry_name());

			List<BookEntry> allBookEntriesFromDb = bookTransactionBackendRecord.getBookEntry();
			List<BookEntry> allBookEntries = record.getBookEntry();

			// boolean found = false;
			// int bookEntriesCount = 0;

			List<BookEntry> bookEntryList = new ArrayList<>();

			boolean addAllNewRecords = false;

			System.out.println(allBookEntries.size() + "--------------" + allBookEntriesFromDb.size());

			int bookEntryIndex = 0;

			BookEntry bookEntryFromDbByIndex = allBookEntriesFromDb.get(bookEntryIndex);

			for (BookEntry updatedBookEntry : allBookEntries) {

				// update
				if (bookEntryIndex < allBookEntriesFromDb.size()) {
					int existingbookEntryId = allBookEntriesFromDb.get(bookEntryIndex).getBook_entry_id();

					if (updatedBookEntry.getBook_entry_id() == existingbookEntryId) {

						// found = true;
						// bookEntriesCount++;
						UpdateExistingBookEntryRecord(allBookEntriesFromDb, bookEntryFromDbByIndex, existingbookEntryId,
								bookEntryIndex, updatedBookEntry);

					}
				}

				// add
				if (allBookEntries.size() > allBookEntriesFromDb.size()) {

					AddNewBookEntryToExistingBookTransaction(updatedBookEntry, bookEntryList);
					// addAllNewRecords = true;
					// bookEntriesCount++;

				}

				bookEntryIndex++;

			}

			// delete
			// checkBookEntryIsDeleted(allBookEntriesFromDb, allBookEntries);

			allBookEntriesFromDb.addAll(bookEntryList);
			bookTransactionRepo.save(bookTransactionBackendRecord);

		}

		else {
			throw new RuntimeException("Invalid booking id");
		}

	}

//	private void checkBookEntryIsDeleted(List<BookEntry> allBookEntriesFromDb, List<BookEntry> allBookEntries) {
//		// for (BookEntry bookEntryFromDb : allBookEntriesFromDb) {
//		
//
//		BookEntry updatedbookEntryByIndex = allBookEntries.get(bookEntryIndex);
//		for (BookEntry bookEntry : allBookEntriesFromDb) {
//			if (bookEntry.getBook_entry_id() == updatedbookEntryByIndex.getBook_entry_id()) {
//				System.out.println(bookEntry.getBook_entry_id() + "  Yes  " + updatedbookEntryByIndex.getBook_entry_id());
//			} else {
//				System.out.println(bookEntry.getBook_entry_id() + "  No  " + updatedbookEntryByIndex.getBook_entry_id());
//
//			}
//		}
//		bookEntryIndex++;

//	}

	// update
	private void UpdateExistingBookEntryRecord(List<BookEntry> allBookEntriesFromDb, BookEntry bookEntryFromDbByIndex,
			int ExistingbookEntryId, int bookEntryIndex, BookEntry updatedBookEntry) {

		bookEntryFromDbByIndex = allBookEntriesFromDb.get(bookEntryIndex);
		System.out.println(bookEntryFromDbByIndex);
		bookEntryFromDbByIndex.setGl_account(updatedBookEntry.getGl_account());
		bookEntryFromDbByIndex.setNature(updatedBookEntry.getNature());

		if (updatedBookEntry.getNature() == NatureEnum.CREDIT) {

			bookEntryFromDbByIndex.setCredit_amt(updatedBookEntry.getCredit_amt());
			bookEntryFromDbByIndex.setDebit_amt(0);
		}
		if (updatedBookEntry.getNature() == NatureEnum.DEBIT) {
			bookEntryFromDbByIndex.setDebit_amt(updatedBookEntry.getDebit_amt());
			bookEntryFromDbByIndex.setCredit_amt(0);
		}

	}

	// add
	private void AddNewBookEntryToExistingBookTransaction(BookEntry updatedBookEntry, List<BookEntry> bookEntryList) {
		if (updatedBookEntry.getBook_entry_id() == 0) {
			BookEntry bookEntry = new BookEntry();
			bookEntry.setCredit_amt(updatedBookEntry.getCredit_amt());
			bookEntry.setDebit_amt(updatedBookEntry.getDebit_amt());
			bookEntry.setGl_account(updatedBookEntry.getGl_account());
			bookEntry.setNature(updatedBookEntry.getNature());
			bookEntry.setDeleted(false);
			bookEntryList.add(bookEntry);
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
