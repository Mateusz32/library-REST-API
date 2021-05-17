package com.restapiforlibrary.library.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "user")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "DATE_OF_CREATE_ACCOUNT")
    @CreationTimestamp //Automatycznie ustawia date w bazie danych
    private LocalDate dateOfCreatAccont;

    @OneToMany(targetEntity = BorrowBook.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JsonIgnoreProperties("user")
    private List<BorrowBook> listBorrowBooks;
}