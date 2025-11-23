package com.itau.loaninsurance.application;

import com.itau.loaninsurance.domain.repository.QuoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.mockito.Mockito.*;

class DeleteQuoteUseCaseTest {

    private QuoteRepository repository;
    private DeleteQuoteUseCase useCase;

    @BeforeEach
    void setup() {
        repository = Mockito.mock(QuoteRepository.class);
        useCase = new DeleteQuoteUseCase(repository);
    }

    @Test
    void shouldDeleteQuoteWhenExists() {
        UUID id = UUID.randomUUID();

        useCase.execute(id);

        verify(repository, times(1)).deleteById(id);
    }

}