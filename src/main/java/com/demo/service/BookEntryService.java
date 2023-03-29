package com.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.demo.dto.UpdateBookTransactionDto;
import com.demo.entity.BookEntry;
import com.demo.entity.NatureEnum;
import com.demo.repo.BookEntryRepo;

@Service
public class BookEntryService {

	private BookEntryRepo bookEntryRepo;

	public BookEntryService(BookEntryRepo bookEntryRepo) {
		this.bookEntryRepo = bookEntryRepo;
	}

	public boolean checkCreditDebitAmtIsEqual(UpdateBookTransactionDto record) {

		List<BookEntry> bookEntries = record.getBookEntry();
		int credit_sum = 0, debit_sum = 0;
		for (BookEntry i : bookEntries) {

			if (i.getNature() == NatureEnum.CREDIT)

				credit_sum = credit_sum + i.getCredit_amt();

			if (i.getNature() == NatureEnum.DEBIT)

				debit_sum = debit_sum + i.getDebit_amt();

		}
		System.out.println(credit_sum + " " + debit_sum);
		if (credit_sum == debit_sum)
			return true;
		else
			return false;

	}

	
	
	
	


}
