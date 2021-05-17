package com.restapiforlibrary.library.testLibrary;

import com.restapiforlibrary.library.domain.Book;
import com.restapiforlibrary.library.domain.BorrowBook;
import com.restapiforlibrary.library.domain.Title;
import com.restapiforlibrary.library.domain.User;
import com.restapiforlibrary.library.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTestSuite {

    @Autowired
    private UserRepository userRepository;
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
        borrowBook.setBook(book);
    }

    @Test
    @Transactional
    public void testUserSave() {
        //GIVEN

        //WHEN
        userRepository.save(user);

        //THEN
        Long id = user.getId();
        Optional<User> readUser = userRepository.findById(id);
        assertTrue(readUser.isPresent());
    }

    @Test
    @Transactional
    public void testUserRead() {
        //GIVEN

        //WHEN
        userRepository.save(user);
        Long id = user.getId();
        User userRead = userRepository.getOne(id);

        //Then
        assertEquals(user, userRead);
    }

    @Test
    @Transactional
    public void testUserUpdate() {
        //GIVEN

        //WHEN
        userRepository.save(user);
        Long id = user.getId();
        User userUpdate = userRepository.getOne(id);
        userUpdate.setSurname("Wąs");
        userRepository.save(userUpdate);
        Long idUpdate = userUpdate.getId();

        //Then
        assertEquals(id, idUpdate);
        assertEquals("Wąs", userUpdate.getSurname());
    }

    @Test
    @Transactional
    public void testUserDelete() {

        //When
        userRepository.save(user);
        Long id = user.getId();

        //Then
        userRepository.deleteById(id);
        assertFalse(userRepository.existsById(id));
    }
}