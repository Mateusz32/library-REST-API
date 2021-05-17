package com.restapiforlibrary.library.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "title")
public class Title {

    @Id
    @GeneratedValue
    @NotNull
    Long id;

    @Column(name = "TITLE")
    String title;

    @Column(name = "AUTHOR")
    String author;

    @Column(name = "YEARPUBLISHED")
    int yearPublished;

    @OneToMany(targetEntity = Book.class,
            mappedBy = "title",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JsonIgnoreProperties("title")
    private List<Book> bookList;
}