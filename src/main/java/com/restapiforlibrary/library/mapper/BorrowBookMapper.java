package com.restapiforlibrary.library.mapper;

import com.restapiforlibrary.library.domain.BorrowBook;
import com.restapiforlibrary.library.domain.BorrowBookDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowBookMapper {

    public BorrowBook mapToBorrowBook(final BorrowBookDto borrowBookDto) {
        return new BorrowBook(
                borrowBookDto.getId(),
                borrowBookDto.getDateOfHire(),
                borrowBookDto.getDateOfBack(),
                borrowBookDto.getUser(),
                borrowBookDto.getBook());
    }

    public BorrowBookDto mapToBorrowBookDto(final BorrowBook borrowBook) {
        return new BorrowBookDto(
                borrowBook.getId(),
                borrowBook.getDateOfHire(),
                borrowBook.getDateOfBack(),
                borrowBook.getUser(),
                borrowBook.getBook()
        );
    }

    public List<BorrowBookDto> mapToBorrowBookDtoList(final List<BorrowBook> borrowBookList) {
        return borrowBookList.stream()
                .map(this::mapToBorrowBookDto)
                .collect(Collectors.toList());
    }
}
