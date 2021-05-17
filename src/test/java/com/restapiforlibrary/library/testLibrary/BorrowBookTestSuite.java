package com.restapiforlibrary.library.testLibrary;

import com.restapiforlibrary.library.domain.Book;
import com.restapiforlibrary.library.domain.BorrowBook;
import com.restapiforlibrary.library.domain.Title;
import com.restapiforlibrary.library.domain.User;
import com.restapiforlibrary.library.repository.BookRepository;
import com.restapiforlibrary.library.repository.BorrowBookRepository;
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
public class BorrowBookTestSuite {

    @Autowired
    private BorrowBookRepository borrowBookRepository;
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

        borrowBook.setUser(user);
        borrowBook.setBook(book);
        book.setTitle(title);
    }

    @Test
    @Transactional
    public void testBorrowBookSave() {
        //GIVEN

        //WHEN
        borrowBookRepository.save(borrowBook);

        //THEN
        Long id = borrowBook.getId();
        Optional<BorrowBook> readBorrowBook = borrowBookRepository.findById(id);
        assertTrue(readBorrowBook.isPresent());
    }

    @Test
    @Transactional
    public void testBorrowBookRead() {
        //GIVEN

        //WHEN
        borrowBookRepository.save(borrowBook);
        Long id = borrowBook.getId();
        BorrowBook borrowBookRead = borrowBookRepository.getOne(id);

        //Then
        assertEquals(borrowBook, borrowBookRead);
    }

    @Test
    @Transactional
    public void testBorrowBookUpdate() {
        //GIVEN
        User updateUser = User.builder()
                .name("Czesław")
                .surname("Wons")
                .dateOfCreatAccont(LocalDate.now())
                .build();

        //WHEN
        borrowBookRepository.save(borrowBook);
        Long id = borrowBook.getId();
        BorrowBook borrowBookUpdate = borrowBookRepository.getOne(id);
        borrowBookUpdate.setUser(updateUser);
        Long idUpdate = borrowBookUpdate.getId();

        //Then
        assertEquals(id, idUpdate);
        assertEquals("Wons", borrowBookUpdate.getUser().getSurname());
    }

    @Test
    @Transactional
    public void testBorrowBookDelete() {

        //When
        borrowBookRepository.save(borrowBook);
        Long id = borrowBook.getId();

        //Then
        borrowBookRepository.deleteById(id);
        assertFalse(borrowBookRepository.existsById(id));
    }
}