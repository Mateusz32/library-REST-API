package com.restapiforlibrary.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BorrowBookDto {

    private Long id;
    private LocalDate dateOfHire;
    private LocalDate dateOfBack;
    private User user;
    private Book book;
}
