package com.restapiforlibrary.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private Long id;
    private String status;
    private Title title;
    private BorrowBook borrowBook;
}
