package com.itau.loaninsurance.domain.repository;

import com.itau.loaninsurance.domain.models.Quote;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface QuoteRepository {
    Quote save(Quote quote);
    Optional<Quote> findById(UUID id);
    List<Quote> findAll();
    void deleteById(UUID id);
}
