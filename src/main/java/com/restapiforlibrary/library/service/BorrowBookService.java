package com.restapiforlibrary.library.service;

import com.restapiforlibrary.library.domain.BorrowBook;
import com.restapiforlibrary.library.repository.BorrowBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BorrowBookService {

    private final BorrowBookRepository borrowBookRepository;

    public Optional<BorrowBook> getBorrowBook(Long id) {
        return borrowBookRepository.findById(id);
    }

    public List<BorrowBook> getBorrowBooks() {
        return borrowBookRepository.findAll();
    }

    public BorrowBook saveBorrowBook(BorrowBook hirebook) {
        return borrowBookRepository.save(hirebook);
    }

    public void deleteBorrowBook(Long id) {
        borrowBookRepository.deleteById(id);
    }
}
