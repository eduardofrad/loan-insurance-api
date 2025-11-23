package com.itau.loaninsurance.application;


import com.itau.loaninsurance.domain.models.Quote;
import com.itau.loaninsurance.domain.repository.QuoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetQuoteUseCaseTest {

    private QuoteRepository repository;
    private GetQuoteUseCase useCase;

    @BeforeEach
    void setup() {
        repository = Mockito.mock(QuoteRepository.class);
        useCase = new GetQuoteUseCase(repository);
    }

    @Test
    void shouldReturnQuoteWhenExists() {
        UUID id = UUID.randomUUID();
        Quote quote = new Quote(
                id,
                BigDecimal.valueOf(1000),
                12,
                BigDecimal.valueOf(0.02),
                BigDecimal.valueOf(0.05),
                BigDecimal.valueOf(20),
                BigDecimal.valueOf(1),
                BigDecimal.valueOf(21),
                Instant.now()
        );

        when(repository.findById(id)).thenReturn(Optional.of(quote));

        Quote result = useCase.execute(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(repository, times(1)).findById(id);
    }

    @Test
    void shouldThrowExceptionWhenQuoteDoesNotExist() {
        UUID id = UUID.randomUUID();
        when(repository.findById(id)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> useCase.execute(id)
        );

        assertEquals("Quote not found: " + id, exception.getMessage());
        verify(repository, times(1)).findById(id);
    }
}
