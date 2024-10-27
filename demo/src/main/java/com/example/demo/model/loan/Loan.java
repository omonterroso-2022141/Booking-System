package com.example.demo.model.loan;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document(collection = "loans")
public class Loan {

    @Id
    private String id;
    private String bookId;
    private String userId;
    private String loanDate;
    private String returnDate;
    private boolean returned;
}
