package com.itau.loaninsurance.domain.models;


import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public final class Quote {

    private final UUID id;
    private final BigDecimal loanAmount;
    private final int termMonths;
    private final BigDecimal premiumRate;
    private final BigDecimal brokerageRate;
    private final BigDecimal premium;
    private final BigDecimal brokerage;
    private final BigDecimal total;
    private final Instant createdAt;


    public Quote(UUID id,
                 BigDecimal loanAmount,
                 int termMonths,
                 BigDecimal premiumRate,
                 BigDecimal brokerageRate,
                 BigDecimal premium,
                 BigDecimal brokerage,
                 BigDecimal total,
                 Instant createdAt) {
        this.id = id;
        this.loanAmount = loanAmount;
        this.termMonths = termMonths;
        this.premiumRate = premiumRate;
        this.brokerageRate = brokerageRate;
        this.premium = premium;
        this.brokerage = brokerage;
        this.total = total;
        this.createdAt = createdAt;
    }

    public UUID getId() { return id; }
    public BigDecimal getLoanAmount() { return loanAmount; }
    public int getTermMonths() { return termMonths; }
    public BigDecimal getPremiumRate() { return premiumRate; }
    public BigDecimal getBrokerageRate() { return brokerageRate; }
    public BigDecimal getPremium() { return premium; }
    public BigDecimal getBrokerage() { return brokerage; }
    public BigDecimal getTotal() { return total; }
    public Instant getCreatedAt() { return createdAt; }
}

