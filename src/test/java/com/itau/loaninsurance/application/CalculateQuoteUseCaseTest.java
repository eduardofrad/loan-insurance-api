package com.itau.loaninsurance.application;



import com.itau.loaninsurance.application.models.QuoteCalculation;
import com.itau.loaninsurance.application.models.QuoteInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class CalculateQuoteUseCaseTest {

    private CalculateQuoteUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new CalculateQuoteUseCase();
    }

    @Test
    void shouldCalculateQuoteCorrectly() {
        QuoteInput input = new QuoteInput(
                new BigDecimal("10000.00"),
                new BigDecimal("0.002"),
                new BigDecimal("0.05")
        );

        QuoteCalculation result = useCase.execute(input);

        assertThat(result.premium()).isEqualByComparingTo("20.00");
        assertThat(result.brokerage()).isEqualByComparingTo("1.00");
        assertThat(result.total()).isEqualByComparingTo("21.00");
        assertThat(result.installmentAmount()).isEqualByComparingTo("1.75");
        assertThat(result.installmentCount()).isEqualTo(12);
    }

    @Test
    void shouldHandleSmallValuesWithCorrectRounding() {
        QuoteInput input = new QuoteInput(
                new BigDecimal("100.00"),
                new BigDecimal("0.00015"),
                new BigDecimal("0.10")
        );

        QuoteCalculation result = useCase.execute(input);

        assertThat(result.premium()).isEqualByComparingTo("0.02");
        assertThat(result.brokerage()).isEqualByComparingTo("0.00");
        assertThat(result.total()).isEqualByComparingTo("0.02");
        assertThat(result.installmentAmount()).isEqualByComparingTo("0.00");
    }

    @Test
    void shouldAlwaysReturn12MonthsAsInsuranceTerm() {
        QuoteInput input = new QuoteInput(
                new BigDecimal("5000"),
                new BigDecimal("0.002"),
                new BigDecimal("0.05")
        );

        QuoteCalculation result = useCase.execute(input);

        assertThat(result.installmentCount()).isEqualTo(12);
    }

    @Test
    void shouldCalculateCorrectlyWhenBrokerageRateIsZero() {
        QuoteInput input = new QuoteInput(
                new BigDecimal("8000"),
                new BigDecimal("0.003"),
                BigDecimal.ZERO
        );

        QuoteCalculation result = useCase.execute(input);

        assertThat(result.premium()).isEqualByComparingTo("24.00");
        assertThat(result.brokerage()).isEqualByComparingTo("0.00");
        assertThat(result.total()).isEqualByComparingTo("24.00");
        assertThat(result.installmentAmount()).isEqualByComparingTo("2.00");
    }

    @Test
    void shouldCalculateCorrectlyWhenPremiumRateIsZero() {
        QuoteInput input = new QuoteInput(
                new BigDecimal("5000"),
                BigDecimal.ZERO,
                new BigDecimal("0.10")
        );

        QuoteCalculation result = useCase.execute(input);

        assertThat(result.premium()).isEqualByComparingTo("0.00");
        assertThat(result.brokerage()).isEqualByComparingTo("0.00");
        assertThat(result.total()).isEqualByComparingTo("0.00");
        assertThat(result.installmentAmount()).isEqualByComparingTo("0.00");
    }
}

