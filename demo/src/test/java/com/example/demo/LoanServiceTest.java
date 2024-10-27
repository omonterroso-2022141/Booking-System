package com.example.demo;

import com.example.demo.model.book.Book;
import com.example.demo.model.loan.Loan;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.LoanRepository;
import com.example.demo.service.loan.LoanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoanServiceTest {

    private LoanRepository loanRepository;
    private BookRepository bookRepository;
    private LoanService loanService;

    @BeforeEach
    void setUp() {
        loanRepository = mock(LoanRepository.class);
        bookRepository = mock(BookRepository.class);
        loanService = new LoanService(loanRepository, bookRepository);
    }

    @Test
    void testCreateLoan_Success() {

        String bookId = "book123";
        String userId = "user123";
        Book book = new Book();
        book.setId(bookId);
        book.setAvailable(true);

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(loanRepository.save(any(Loan.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Loan loan = loanService.createLoan(bookId, userId);

        assertNotNull(loan);
        assertEquals(bookId, loan.getBookId());
        assertEquals(userId, loan.getUserId());
        assertEquals(LocalDate.now().toString(), loan.getLoanDate());
        assertFalse(loan.isReturned());
        verify(bookRepository).save(book);
    }


    @Test
    void testReturnBook_LoanNotFound() {
        String loanId = "loan123";

        when(loanRepository.findById(loanId)).thenReturn(Optional.empty());

        Loan returnedLoan = loanService.returnBook(loanId);

        assertNull(returnedLoan);
        verify(bookRepository, never()).save(any());
    }

    @Test
    void testGetLoansByUserId() {
        String userId = "user123";
        Loan loan1 = new Loan();
        Loan loan2 = new Loan();

        when(loanRepository.findByUserId(userId)).thenReturn(List.of(loan1, loan2));

        List<Loan> loans = loanService.getLoansByUserId(userId);

        assertNotNull(loans);
        assertEquals(2, loans.size());
        verify(loanRepository).findByUserId(userId);
    }
}
