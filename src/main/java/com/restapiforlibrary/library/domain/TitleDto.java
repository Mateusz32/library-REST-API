package com.restapiforlibrary.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TitleDto {

    Long id;
    String title;
    String author;
    int yearPublished;
    private List<Book> bookList;
}