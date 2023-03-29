package com.demo.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.entity.BookTransaction;


@Repository
public interface BookTransactionRepo extends JpaRepository<BookTransaction, Integer>{
	
	
	
}
