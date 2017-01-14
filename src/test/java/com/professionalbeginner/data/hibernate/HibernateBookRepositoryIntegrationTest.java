package com.professionalbeginner.data.hibernate;

import com.professionalbeginner.TestUtils;
import com.professionalbeginner.domain.core.book.Book;
import com.professionalbeginner.domain.core.book.BookId;
import com.professionalbeginner.domain.interfacelayer.repository.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Kempenich Florian
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration-tests")
public class HibernateBookRepositoryIntegrationTest {

    // TODO: 12/10/2016 Also implement integration tests on service, or even controller

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TestUtils testUtils;

    @Test
    public void saveAndFind_withoutReviews() throws Exception {
        String id = "id-1";

        Book toSave = testUtils.makeDefaultBook(id);

        assertEquals(id, toSave.id().idString());

        BookId savedId = bookRepository.save(toSave);
        assertNotEquals(id, savedId.idString()); //Check that repository updates the id

        Book fromRepo = bookRepository.findById(savedId, false);

        assertEquals(savedId, fromRepo.id());
        assertTrue(areBooksSimilar_ignoreReviews(toSave, fromRepo));
    }


//    @Test
//    public void saveAndFind_withReviews() throws Exception {
//        String id = "id-1";
//
//        Book toSave = testUtils.makeDefaultBook(id);
//
//        assertEquals(id, toSave.id().idString());
//
//        BookId savedId = bookRepository.save(toSave);
//        assertNotEquals(id, savedId.idString()); //Check that repository updates the id
//
//        Book fromRepo = bookRepository.findById(savedId, false);
//
//        assertEquals(savedId, fromRepo.id());
//        assertTrue(areBooksSimilar_ignoreReviews(toSave, fromRepo));
//    }

//    @Test
//    public void saveMultiple_findAll() throws Exception {
//        Book book1 = testUtils.makeBook("id-1", "title", "author", 39, 53.12);
//        Book book2 = testUtils.makeBook("id-2", "Learn portuguese", "Alexandra", 321, 124);
//        Book book3 = testUtils.makeBook("id-3", "Third book", "Philip", 665, 53.2);
//
//        bookRepository.save(book1);
//        bookRepository.save(book2);
//        bookRepository.save(book3);
//
//        List<Book> fromRepo = bookRepository.findAll(false);
//
//        assertEquals(3, fromRepo.size());
//        assertListContainsSimilarBook(book1, fromRepo);
//        assertListContainsSimilarBook(book2, fromRepo);
//        assertListContainsSimilarBook(book3, fromRepo);
//    }
//
//    private void assertListContainsSimilarBook(Book toCheck, List<Book> bookList) {
//        boolean contains = bookList.stream()
//                .anyMatch(book -> areBooksSimilar(toCheck, book));
//
//        assertTrue("List does not contain book: " + toCheck, contains);
//    }
//
    private boolean areBooksSimilar_ignoreReviews(Book toCheck, Book book) {
        return toCheck.characteristics().title().equals(book.characteristics().title()) &&
                toCheck.characteristics().author().equals(book.characteristics().author()) &&
                toCheck.characteristics().numPages() == book.characteristics().numPages() &&
                Double.compare(toCheck.price().amount(), book.price().amount()) == 0;
    }
}