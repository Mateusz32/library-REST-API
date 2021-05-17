package com.restapiforlibrary.library.controller;

import com.restapiforlibrary.library.domain.BorrowBook;
import com.restapiforlibrary.library.domain.BorrowBookDto;
import com.restapiforlibrary.library.mapper.BorrowBookMapper;
import com.restapiforlibrary.library.service.BorrowBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/borrowbook")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BorrowBookController {

    private final BorrowBookService borrowBookService;
    private final BorrowBookMapper borrowBookMapper;

    @GetMapping("/getBorrowBooks")
    public List<BorrowBookDto> getBorrowBooks() {
        List<BorrowBook> listBorrowBooks = borrowBookService.getBorrowBooks();
        return borrowBookMapper.mapToBorrowBookDtoList(listBorrowBooks);
    }

    @GetMapping("/getBorrowBook")
    public BorrowBookDto getBorrowBook(@RequestParam Long borrowBookId) throws NotFoundException {
        BorrowBook borrowBook = borrowBookService.getBorrowBook(borrowBookId).orElseThrow(NotFoundException::new);
        return borrowBookMapper.mapToBorrowBookDto(borrowBook);
    }

    @PostMapping(value = "/createBorrowBook", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createBorrowBook(@RequestBody BorrowBookDto borrowBookDto) {
        if (borrowBookDto.getDateOfHire() == null) {
            BorrowBook borrowBook = borrowBookMapper.mapToBorrowBook(borrowBookDto);
            borrowBook.setDateOfHire(LocalDate.now());
            borrowBook.setDateOfBack(null);
            borrowBookService.saveBorrowBook(borrowBook);
        }
    }

    @PutMapping("/editBorrowBook")
    public BorrowBookDto editBorrowBook(@RequestBody BorrowBookDto borrowBookDto) {
        BorrowBook borrowBook = borrowBookMapper.mapToBorrowBook(borrowBookDto);
        BorrowBook saveBorrowBook = borrowBookService.saveBorrowBook(borrowBook);
        return borrowBookMapper.mapToBorrowBookDto(saveBorrowBook);
    }

    @DeleteMapping("/deleteBorrowBook")
    public void deleteBorrowBook(@RequestParam Long borrowBookId) {
        borrowBookService.deleteBorrowBook(borrowBookId);
    }

    @PatchMapping("backBorrowBook")
    public BorrowBookDto backBorrowBook(@RequestParam Long borrowBookId) throws NotFoundException{
        BorrowBook borrowBook = borrowBookService.getBorrowBook(borrowBookId).orElseThrow(NotFoundException::new);
        LocalDate dateOfBack = LocalDate.now();
        borrowBook.setDateOfBack(dateOfBack);
        BorrowBook saveBorrowBook = borrowBookService.saveBorrowBook(borrowBook);
        return borrowBookMapper.mapToBorrowBookDto(saveBorrowBook);
    }
}