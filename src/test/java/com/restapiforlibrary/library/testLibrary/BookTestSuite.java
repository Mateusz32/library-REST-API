package com.restapiforlibrary.library.testLibrary;

import com.restapiforlibrary.library.domain.Book;
import com.restapiforlibrary.library.domain.BorrowBook;
import com.restapiforlibrary.library.domain.Title;
import com.restapiforlibrary.library.domain.User;
import com.restapiforlibrary.library.repository.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookTestSuite {

    @Autowired
    private BookRepository bookRepository;
    private User user;
    private Title title;
    private Book book;
    private BorrowBook borrowBook;

    @Before
    public void beforeTest() {
        user = User.builder()
                .name("Czesław")
                .surname("Wons")
                .dateOfCreatAccont(LocalDate.now())
                .build();

        title = Title.builder()
                .title("Krzyżacy")
                .author("Henryk Sienkiewicz")
                .yearPublished(1999)
                .build();

        book = Book.builder()
                .status("demaged")
                .build();

        borrowBook = BorrowBook.builder()
                .dateOfBack(null)
                .build();

        List<BorrowBook> listBorrowBookForUser = new ArrayList<>();
        listBorrowBookForUser.add(borrowBook);

        user.setListBorrowBooks(listBorrowBookForUser);
        book.setTitle(title);
        book.setBorrowBook(borrowBook);
    }

    @Test
    @Transactional
    public void testTitleSave() {
        //GIVEN

        //WHEN
        bookRepository.save(book);

        //THEN
        Long id = book.getId();
        Optional<Book> readBook = bookRepository.findById(id);
        assertTrue(readBook.isPresent());
    }

    @Test
    @Transactional
    public void testTitleRead() {
        //GIVEN

        //WHEN
        bookRepository.save(book);
        Long id = book.getId();
        Book bookRead = bookRepository.getOne(id);

        //Then
        assertEquals(book, bookRead);
    }

    @Test
    @Transactional
    public void testTitleUpdate() {
        //GIVEN

        //WHEN
        bookRepository.save(book);
        Long id = book.getId();
        Book bookUpdate = bookRepository.getOne(id);
        bookUpdate.setStatus("borrowed");
        bookRepository.save(bookUpdate);
        Long idUpdate = bookUpdate.getId();

        //Then
        assertEquals(id, idUpdate);
    }

    @Test
    @Transactional
    public void testTitleDelete() {

        //When
        bookRepository.save(book);
        Long id = book.getId();

        //Then
        bookRepository.deleteById(id);
        assertFalse(bookRepository.existsById(id));
    }
}