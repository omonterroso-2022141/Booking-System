package com.example.demo.controller.loan;

import com.example.demo.dto.LoanRequest;
import com.example.demo.model.loan.Loan;
import com.example.demo.service.loan.LoanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/borrow")
    public Loan borrowBook(@RequestBody LoanRequest loanRequest) {
        return loanService.createLoan(loanRequest.getBookId(), loanRequest.getUserId());
    }


    @PutMapping("/return/{loanId}")
    public Loan returnBook(@PathVariable String loanId) {
        return loanService.returnBook(loanId);
    }

    @GetMapping("/user/{userId}")
    public List<Loan> getUserLoans(@PathVariable String userId) {
        return loanService.getLoansByUserId(userId);
    }
    @GetMapping("/all")
    public List<Loan> getAllLoans() {
        return loanService.getAllLoans();
    }
}
