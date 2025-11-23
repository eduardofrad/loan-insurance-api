package com.itau.loaninsurance.application;

import com.itau.loaninsurance.domain.models.Quote;
import com.itau.loaninsurance.domain.repository.QuoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ListQuotesUseCaseTest {

    private QuoteRepository repository;
    private ListQuotesUseCase useCase;

    @BeforeEach
    void setup() {
        repository = Mockito.mock(QuoteRepository.class);
        useCase = new ListQuotesUseCase(repository);
    }

    @Test
    void shouldReturnListOfQuotes() {
        Quote q1 = new Quote(
                UUID.randomUUID(),
                BigDecimal.valueOf(1000),
                12,
                BigDecimal.valueOf(0.02),
                BigDecimal.valueOf(0.05),
                BigDecimal.valueOf(20),
                BigDecimal.valueOf(1),
                BigDecimal.valueOf(21),
                Instant.now()
        );

        Quote q2 = new Quote(
                UUID.randomUUID(),
                BigDecimal.valueOf(2000),
                12,
                BigDecimal.valueOf(0.02),
                BigDecimal.valueOf(0.05),
                BigDecimal.valueOf(40),
                BigDecimal.valueOf(2),
                BigDecimal.valueOf(42),
                Instant.now()
        );

        when(repository.findAll()).thenReturn(List.of(q1, q2));

        List<Quote> result = useCase.execute();

        assertEquals(2, result.size());
        assertTrue(result.contains(q1));
        assertTrue(result.contains(q2));
        verify(repository, times(1)).findAll();
    }

    @Test
    void shouldReturnEmptyListWhenNoQuotesExist() {
        when(repository.findAll()).thenReturn(List.of());

        List<Quote> result = useCase.execute();

        assertTrue(result.isEmpty());
        verify(repository, times(1)).findAll();
    }
}

