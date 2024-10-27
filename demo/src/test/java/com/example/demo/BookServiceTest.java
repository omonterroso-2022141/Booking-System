package com.example.demo;

import com.example.demo.model.book.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.book.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {

    private BookRepository bookRepository;
    private BookService bookService;

    @BeforeEach
    void setUp() {
        bookRepository = mock(BookRepository.class);
        bookService = new BookService(bookRepository);
    }

    @Test
    void testCreateBook_Success() {
        Book book = new Book();
        book.setId("book1");
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setAvailable(true);

        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Book createdBook = bookService.createBook(book);

        assertNotNull(createdBook);
        assertEquals(book.getId(), createdBook.getId());
        verify(bookRepository).save(book);
    }

    @Test
    void testGetAllBooks_Success() {
        Book book1 = new Book();
        book1.setId("book1");
        book1.setTitle("Test Book 1");

        Book book2 = new Book();
        book2.setId("book2");
        book2.setTitle("Test Book 2");

        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);

        when(bookRepository.findAll()).thenReturn(books);

        List<Book> foundBooks = bookService.getAllBooks();

        assertNotNull(foundBooks);
        assertEquals(2, foundBooks.size());
        verify(bookRepository).findAll();
    }

    @Test
    void testGetBookById_Success() {
        String bookId = "book1";
        Book book = new Book();
        book.setId(bookId);
        book.setTitle("Test Book");

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        Book foundBook = bookService.getBookById(bookId);

        assertNotNull(foundBook);
        assertEquals(bookId, foundBook.getId());
        verify(bookRepository).findById(bookId);
    }

    @Test
    void testGetBookById_NotFound() {
        String bookId = "book1";
        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        Book foundBook = bookService.getBookById(bookId);

        assertNull(foundBook);
        verify(bookRepository).findById(bookId);
    }

    @Test
    void testUpdateBook_Success() {
        String bookId = "book1";
        Book existingBook = new Book();
        existingBook.setId(bookId);
        existingBook.setTitle("Old Title");
        existingBook.setAuthor("Old Author");
        existingBook.setAvailable(true);

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(existingBook));
        when(bookRepository.save(any(Book.class))).thenReturn(existingBook);

        Book updatedBook = new Book();
        updatedBook.setTitle("New Title");
        updatedBook.setAuthor("New Author");
        updatedBook.setAvailable(false);

        Book result = bookService.updateBook(bookId, updatedBook);

        assertNotNull(result);
        assertEquals("New Title", result.getTitle());
        verify(bookRepository).save(existingBook);
    }

    @Test
    void testUpdateBook_NotFound() {
        String bookId = "book1";
        Book updatedBook = new Book();
        updatedBook.setTitle("New Title");
        updatedBook.setAuthor("New Author");

        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        Book result = bookService.updateBook(bookId, updatedBook);

        assertNull(result);
        verify(bookRepository, never()).save(any());
    }

    @Test
    void testDeleteBook_Success() {
        String bookId = "book1";
        doNothing().when(bookRepository).deleteById(bookId);

        bookService.deleteBook(bookId);

        verify(bookRepository).deleteById(bookId);
    }

    @Test
    void testDeleteBook_NotFound() {
        String bookId = "book1";
        doThrow(new IllegalArgumentException("No such book")).when(bookRepository).deleteById(bookId);

        assertThrows(IllegalArgumentException.class, () -> bookService.deleteBook(bookId));
    }
}
