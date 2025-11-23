package com.itau.loaninsurance.application.models;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record QuoteInput(
        @NotNull @DecimalMin("0.01")
        BigDecimal loanAmount,

        @NotNull
        BigDecimal premiumRate,

        @NotNull
        BigDecimal brokerageRate
) {}

