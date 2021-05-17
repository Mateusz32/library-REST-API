package com.restapiforlibrary.library.domain;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "books")
public class Book {

    @Id
    @NotNull
    @GeneratedValue
    private Long id;

    @Column(name = "STATUS")
    private String status;

    @ManyToOne
    @JoinColumn(name = "TITLE_ID")
    @JsonIgnoreProperties("bookList")
    private Title title;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "BORROWBOOK_ID")
    @JsonIgnoreProperties("book")
    private BorrowBook borrowBook;
}