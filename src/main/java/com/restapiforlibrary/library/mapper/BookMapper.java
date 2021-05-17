package com.restapiforlibrary.library.mapper;

import com.restapiforlibrary.library.domain.Book;
import com.restapiforlibrary.library.domain.BookDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookMapper {

    public Book mapToBook(final BookDto bookDto) {
        return new Book(
                bookDto.getId(),
                bookDto.getStatus(),
                bookDto.getTitle(),
                bookDto.getBorrowBook()
        );
    }

    public BookDto mapToBookDto(final Book book) {
        return new BookDto(
                book.getId(),
                book.getStatus(),
                book.getTitle(),
                book.getBorrowBook()
        );
    }

    public List<BookDto> mapToBookDtoList(final List<Book> bookList) {
        return bookList.stream()
                .map(this::mapToBookDto)
                .collect(Collectors.toList());
    }
}