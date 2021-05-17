package com.restapiforlibrary.library.testLibrary;

import com.restapiforlibrary.library.domain.Book;
import com.restapiforlibrary.library.domain.BorrowBook;
import com.restapiforlibrary.library.domain.Title;
import com.restapiforlibrary.library.domain.User;
import com.restapiforlibrary.library.repository.TitleRepository;
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
public class TitleTestSuite {

    @Autowired
    private TitleRepository titleRepository;
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
                .borrowBook(borrowBook)
                .build();

        borrowBook = BorrowBook.builder()
                .dateOfBack(null)
                .build();

        List<BorrowBook> listBorrowBookForUser = new ArrayList<>();
        listBorrowBookForUser.add(borrowBook);

        List<Book> listBookForTitle = new ArrayList<>();
        listBookForTitle.add(book);

        user.setListBorrowBooks(listBorrowBookForUser);
        title.setBookList(listBookForTitle);
        borrowBook.setBook(book);
    }

    @Test
    @Transactional
    public void testTitleSave() {
        //GIVEN

        //WHEN
        titleRepository.save(title);

        //THEN
        Long id = title.getId();
        Optional<Title> readTitle = titleRepository.findById(id);
        assertTrue(readTitle.isPresent());
    }

    @Test
    @Transactional
    public void testTitleRead() {
        //GIVEN

        //WHEN
        titleRepository.save(title);
        Long id = title.getId();
        Title titleRead = titleRepository.getOne(id);

        //Then
        assertEquals(title, titleRead);
    }

    @Test
    @Transactional
    public void testTitleUpdate() {
        //GIVEN

        //WHEN
        titleRepository.save(title);
        Long id = title.getId();
        Title titleUpdate = titleRepository.getOne(id);
        titleUpdate.setTitle("Chłopi");
        titleUpdate.setAuthor("Władysław Reymont");
        titleUpdate.setYearPublished(2019);
        titleRepository.save(titleUpdate);
        Long idUpdate = titleUpdate.getId();

        //Then
        assertEquals(id, idUpdate);
        assertEquals("Chłopi", titleRepository.getOne(id).getTitle());
        assertEquals("Władysław Reymont", titleRepository.getOne(id).getAuthor());
        assertEquals(2019, titleRepository.getOne(id).getYearPublished());
    }

    @Test
    @Transactional
    public void testTitleDelete() {

        //When
        titleRepository.save(title);
        Long id = title.getId();

        //Then
        titleRepository.deleteById(id);
        assertFalse(titleRepository.existsById(id));
    }
}