package com.itau.loaninsurance.application.models;


import java.math.BigDecimal;

public record QuoteCalculation(
        BigDecimal premium,
        BigDecimal brokerage,
        BigDecimal total,
        BigDecimal installmentAmount,
        int installmentCount
) {}

