package com.itau.loaninsurance.infrastructure.persistence;


import com.itau.loaninsurance.domain.models.Quote;
import com.itau.loaninsurance.domain.repository.QuoteRepository;
import com.itau.loaninsurance.infrastructure.persistence.entity.QuoteEntity;
import com.itau.loaninsurance.infrastructure.persistence.springdata.SpringDataQuoteRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class QuoteRepositoryImpl implements QuoteRepository {

    private final SpringDataQuoteRepository jpa;

    public QuoteRepositoryImpl(SpringDataQuoteRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    @Transactional
    public Quote save(Quote quote) {
        QuoteEntity entity = toEntity(quote);
        QuoteEntity saved = jpa.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<Quote> findById(UUID id) {
        return jpa.findById(id.toString()).map(this::toDomain);
    }

    @Override
    public List<Quote> findAll() {
        return jpa.findAll().stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        jpa.deleteById(id.toString());
    }

    private QuoteEntity toEntity(Quote q) {
        return new QuoteEntity(
                q.getId().toString(),
                q.getLoanAmount(),
                q.getTermMonths(),
                q.getPremiumRate(),
                q.getBrokerageRate(),
                q.getPremium(),
                q.getBrokerage(),
                q.getTotal(),
                q.getCreatedAt()
        );
    }

    private Quote toDomain(QuoteEntity e) {
        return new Quote(
                UUID.fromString(e.getId()),
                e.getLoanAmount(),
                e.getTermMonths(),
                e.getPremiumRate(),
                e.getBrokerageRate(),
                e.getPremium(),
                e.getBrokerage(),
                e.getTotal(),
                e.getCreatedAt()
        );
    }
}


