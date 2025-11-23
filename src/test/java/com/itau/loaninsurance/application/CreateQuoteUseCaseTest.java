package com.itau.loaninsurance.application;

import com.itau.loaninsurance.application.models.QuoteCalculation;
import com.itau.loaninsurance.application.models.QuoteInput;
import com.itau.loaninsurance.domain.models.Quote;
import com.itau.loaninsurance.domain.repository.QuoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CreateQuoteUseCaseTest {

    private QuoteRepository repository;
    private CalculateQuoteUseCase calculateQuoteUseCase;
    private CreateQuoteUseCase useCase;

    @BeforeEach
    void setUp() {
        repository = mock(QuoteRepository.class);
        calculateQuoteUseCase = mock(CalculateQuoteUseCase.class);
        useCase = new CreateQuoteUseCase(repository, calculateQuoteUseCase);
    }

    @Test
    void shouldCreateAndPersistQuoteSuccessfully() {
        QuoteInput input = new QuoteInput(
                new BigDecimal("10000.00"),
                new BigDecimal("0.002"),
                new BigDecimal("0.05")
        );

        QuoteCalculation calc = new QuoteCalculation(
                new BigDecimal("20.00"),
                new BigDecimal("1.00"),
                new BigDecimal("21.00"),
                new BigDecimal("1.75"),
                12
        );

        when(calculateQuoteUseCase.execute(input)).thenReturn(calc);

        Quote persisted = new Quote(
                UUID.randomUUID(),
                input.loanAmount(),
                calc.installmentCount(),
                input.premiumRate(),
                input.brokerageRate(),
                calc.premium(),
                calc.brokerage(),
                calc.total(),
                Instant.now()
        );

        when(repository.save(any())).thenReturn(persisted);

        Quote result = useCase.execute(input);

        assertThat(result).isNotNull();
        assertThat(result.getLoanAmount()).isEqualByComparingTo("10000.00");
        assertThat(result.getTermMonths()).isEqualTo(12);
        assertThat(result.getPremiumRate()).isEqualByComparingTo("0.002");
        assertThat(result.getBrokerageRate()).isEqualByComparingTo("0.05");
        assertThat(result.getPremium()).isEqualByComparingTo("20.00");
        assertThat(result.getBrokerage()).isEqualByComparingTo("1.00");
        assertThat(result.getTotal()).isEqualByComparingTo("21.00");

        verify(calculateQuoteUseCase, times(1)).execute(input);
    }

    @Test
    void shouldCaptureQuotePassedToRepository() {
        QuoteInput input = new QuoteInput(
                new BigDecimal("5000"),
                new BigDecimal("0.003"),
                new BigDecimal("0.10")
        );

        QuoteCalculation calc = new QuoteCalculation(
                new BigDecimal("15.00"),
                new BigDecimal("1.50"),
                new BigDecimal("16.50"),
                new BigDecimal("1.37"),
                12
        );

        when(calculateQuoteUseCase.execute(input)).thenReturn(calc);
        when(repository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        Quote result = useCase.execute(input);

        ArgumentCaptor<Quote> captor = ArgumentCaptor.forClass(Quote.class);
        verify(repository).save(captor.capture());

        Quote saved = captor.getValue();

        assertThat(saved.getLoanAmount()).isEqualByComparingTo("5000");
        assertThat(saved.getTermMonths()).isEqualTo(12);
        assertThat(saved.getPremium()).isEqualByComparingTo("15.00");
        assertThat(saved.getBrokerage()).isEqualByComparingTo("1.50");
        assertThat(saved.getTotal()).isEqualByComparingTo("16.50");
        assertThat(saved.getCreatedAt()).isNotNull();
        assertThat(saved.getId()).isNotNull();

        assertThat(result.getId()).isEqualTo(saved.getId());
    }

    @Test
    void shouldGenerateIdAndCreatedAtInQuote() {
        QuoteInput input = new QuoteInput(
                new BigDecimal("2000"),
                new BigDecimal("0.001"),
                new BigDecimal("0.10")
        );

        QuoteCalculation calc = new QuoteCalculation(
                new BigDecimal("2.00"),
                new BigDecimal("0.20"),
                new BigDecimal("2.20"),
                new BigDecimal("0.18"),
                12
        );

        when(calculateQuoteUseCase.execute(input)).thenReturn(calc);
        when(repository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        Quote result = useCase.execute(input);

        assertThat(result.getId()).isNotNull();
        assertThat(result.getCreatedAt()).isNotNull();
        assertThat(result.getTotal()).isEqualByComparingTo("2.20");
        assertThat(result.getTermMonths()).isEqualTo(12);
    }
}
