package com.itau.loaninsurance.application;


import com.itau.loaninsurance.application.models.QuoteCalculation;
import com.itau.loaninsurance.domain.models.Quote;
import com.itau.loaninsurance.domain.repository.QuoteRepository;
import com.itau.loaninsurance.application.models.QuoteInput;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;


@Service
public class CreateQuoteUseCase {

    private final QuoteRepository repository;
    private final CalculateQuoteUseCase calculateQuoteUseCase;

    public CreateQuoteUseCase(QuoteRepository repository, CalculateQuoteUseCase calculateQuoteUseCase) {
        this.repository = repository;
        this.calculateQuoteUseCase = calculateQuoteUseCase;
    }

    public Quote execute(QuoteInput request) {
        final UUID id = UUID.randomUUID();
        final QuoteCalculation quoteCalculation = calculateQuoteUseCase.execute(request);
        final Quote quote = new Quote(id,
                request.loanAmount(),
                quoteCalculation.installmentCount(),
                request.premiumRate(),
                request.brokerageRate(),
                quoteCalculation.premium(),
                quoteCalculation.brokerage(),
                quoteCalculation.total(),
                Instant.now()
        );
        return repository.save(quote);
    }
}
