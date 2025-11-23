package com.itau.loaninsurance.application;

import com.itau.loaninsurance.application.models.QuoteCalculation;
import com.itau.loaninsurance.application.models.QuoteInput;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CalculateQuoteUseCase {

    private static final int INSURANCE_TERM_MONTHS = 12;

    public QuoteCalculation execute(QuoteInput quote) {
        BigDecimal loanAmount = quote.loanAmount();
        BigDecimal premiumRate = quote.premiumRate();
        BigDecimal brokerageRate = quote.brokerageRate();

        BigDecimal premium = loanAmount
                .multiply(premiumRate)
                .setScale(2, RoundingMode.HALF_UP);

        BigDecimal brokerage = premium
                .multiply(brokerageRate)
                .setScale(2, RoundingMode.HALF_UP);

        BigDecimal total = premium.add(brokerage)
                .setScale(2, RoundingMode.HALF_UP);

        BigDecimal installment = total
                .divide(BigDecimal.valueOf(INSURANCE_TERM_MONTHS), 2, RoundingMode.HALF_UP);

        return new QuoteCalculation(
                premium,
                brokerage,
                total,
                installment,
                INSURANCE_TERM_MONTHS
        );
    }
}
