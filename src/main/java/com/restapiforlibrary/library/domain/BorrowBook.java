package com.restapiforlibrary.library.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "borrowbook")
public class BorrowBook {

    @Id
    @NotNull
    @GeneratedValue
    private Long id;

    @Column(name = "DATE_OF_BORROW")
    private LocalDate dateOfHire;

    @Column(name = "DATE_OF_BACK")
    private LocalDate dateOfBack;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    @JsonIgnoreProperties("listHireBooks")
    private User user;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "BOOK_ID")
    @JsonIgnoreProperties("hireBook")
    private Book book;
}