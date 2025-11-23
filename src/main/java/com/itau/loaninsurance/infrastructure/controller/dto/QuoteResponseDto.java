package com.itau.loaninsurance.infrastructure.controller.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record QuoteResponseDto(
        UUID id,
        BigDecimal loanAmount,
        int termMonths,
        BigDecimal premiumRate,
        BigDecimal brokerageRate,
        BigDecimal premium,
        BigDecimal brokerage,
        BigDecimal total,
        Instant createdAt
) {}

