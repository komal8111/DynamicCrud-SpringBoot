package com.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.entity.BookEntry;


@Repository
public interface BookEntryRepo extends JpaRepository<BookEntry, Integer>{

}
