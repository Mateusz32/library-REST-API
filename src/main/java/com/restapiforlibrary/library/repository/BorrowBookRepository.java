package com.restapiforlibrary.library.repository;

import com.restapiforlibrary.library.domain.BorrowBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowBookRepository extends JpaRepository<BorrowBook, Long> {

}