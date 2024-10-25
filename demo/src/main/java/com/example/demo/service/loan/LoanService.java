package com.example.demo.service.loan;

import com.example.demo.model.book.Book;
import com.example.demo.model.loan.Loan;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;

    public LoanService(LoanRepository loanRepository, BookRepository bookRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
    }

    public Loan createLoan(String bookId, String userId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        if (book == null || !book.isAvailable()) {
            throw new IllegalStateException("The book is not available");
        }

        book.setAvailable(false);
        bookRepository.save(book);

        Loan loan = Loan.builder()
                .bookId(bookId)
                .userId(userId)
                .loanDate(LocalDate.now().toString())
                .returned(false)
                .build();
        return loanRepository.save(loan);
    }

    public Loan returnBook(String loanId) {
        Loan loan = loanRepository.findById(loanId).orElse(null);
        if (loan != null && !loan.isReturned()) {
            loan.setReturned(true);
            loan.setReturnDate(LocalDate.now().toString());

            Book book = bookRepository.findById(loan.getBookId()).orElse(null);
            if (book != null) {
                book.setAvailable(true);
                bookRepository.save(book);
            }

            return loanRepository.save(loan);
        }
        return null;
    }

    public List<Loan> getLoansByUserId(String userId) {
        return loanRepository.findByUserId(userId);
    }

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }
}
