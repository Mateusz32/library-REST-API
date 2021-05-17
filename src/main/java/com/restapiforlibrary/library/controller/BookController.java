package com.restapiforlibrary.library.controller;

import com.restapiforlibrary.library.domain.Book;
import com.restapiforlibrary.library.domain.BookDto;
import com.restapiforlibrary.library.domain.Title;
import com.restapiforlibrary.library.mapper.BookMapper;
import com.restapiforlibrary.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @GetMapping("/getBook")
    public BookDto getBook(@RequestParam Long bookId) throws NotFoundException {
        return bookMapper.mapToBookDto(bookService.getBook(bookId).orElseThrow(NotFoundException::new));
    }

    @GetMapping("/getBooks")
    public List<BookDto> getBooks() {
        List<Book> listBooks = bookService.getBooks();
        return bookMapper.mapToBookDtoList(listBooks);
    }

    @GetMapping("/getBooksAvailable")
    public int getBooksAvailable(@RequestParam String title) {
        List<Book> listBooks = bookService.getBooks().stream()
                .filter(book -> book.getTitle()!=null)
                .filter(book -> book.getTitle().getTitle().toLowerCase().equals(title.toLowerCase()))
                .filter(book -> book.getStatus().toLowerCase().equals("available"))
                .collect(Collectors.toList());
         int quantity = listBooks.size();
        return quantity;
    }

    @PostMapping(value = "/createBook", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createBook(@RequestBody BookDto bookDto) {
        bookService.saveBook(bookMapper.mapToBook(bookDto));
    }

    @PutMapping("/editBook")
    public BookDto editBook(@RequestBody BookDto bookDto) {
        Book book = bookMapper.mapToBook(bookDto);
        Book savedBook = bookService.saveBook(book);
        return bookMapper.mapToBookDto(savedBook);
    }

    @DeleteMapping("/deleteBook")
    public void deleteBook(@RequestParam Long bookId) {
        bookService.deleteBook(bookId);
    }

    @PatchMapping("/editBookStatus")
    public BookDto editBookStatus(@RequestParam Long bookId, String status) throws NotFoundException {
        Book book = bookService.getBook(bookId).orElseThrow(NotFoundException::new);
        book.setStatus(status);
        Book savedBook = bookService.saveBook(book);
        return bookMapper.mapToBookDto(savedBook);
    }
}