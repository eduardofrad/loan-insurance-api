package com.itau.loaninsurance.application;



import com.itau.loaninsurance.domain.models.Quote;
import com.itau.loaninsurance.domain.repository.QuoteRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetQuoteUseCase {

    private final QuoteRepository repository;

    public GetQuoteUseCase(QuoteRepository repository) { this.repository = repository; }

    public Quote execute(UUID id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Quote not found: " + id));
    }
}
