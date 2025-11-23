package com.itau.loaninsurance.application;

import com.itau.loaninsurance.domain.repository.QuoteRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteQuoteUseCase {

    private final QuoteRepository repository;

    public DeleteQuoteUseCase(QuoteRepository repository) {
        this.repository = repository;
    }

    public void execute(UUID id) {
        repository.deleteById(id);
    }
}
