package com.itau.loaninsurance.application;


import com.itau.loaninsurance.domain.models.Quote;
import com.itau.loaninsurance.domain.repository.QuoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListQuotesUseCase {

    private final QuoteRepository repository;

    public ListQuotesUseCase(QuoteRepository repository) { this.repository = repository; }

    public List<Quote> execute() {
        return repository.findAll();
    }
}

