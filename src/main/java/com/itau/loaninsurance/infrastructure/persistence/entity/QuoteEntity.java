package com.itau.loaninsurance.infrastructure.persistence.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "quotes")
public class QuoteEntity {

    @Id
    @Column(length = 36)
    private String id;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal loanAmount;

    @Column(nullable = false)
    private int termMonths;

    @Column(nullable = false, precision = 19, scale = 8)
    private BigDecimal premiumRate;

    @Column(nullable = false, precision = 19, scale = 8)
    private BigDecimal brokerageRate;

    @Column(precision = 19, scale = 4)
    private BigDecimal premium;

    @Column(precision = 19, scale = 4)
    private BigDecimal brokerage;

    @Column(precision = 19, scale = 4)
    private BigDecimal total;

    private Instant createdAt;

    public QuoteEntity() {}

    public QuoteEntity(String id,
                       java.math.BigDecimal loanAmount,
                       int termMonths,
                       java.math.BigDecimal premiumRate,
                       java.math.BigDecimal brokerageRate,
                       java.math.BigDecimal premium,
                       java.math.BigDecimal brokerage,
                       java.math.BigDecimal total,
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

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public java.math.BigDecimal getLoanAmount() { return loanAmount; }
    public void setLoanAmount(java.math.BigDecimal loanAmount) { this.loanAmount = loanAmount; }
    public int getTermMonths() { return termMonths; }
    public void setTermMonths(int termMonths) { this.termMonths = termMonths; }
    public java.math.BigDecimal getPremiumRate() { return premiumRate; }
    public void setPremiumRate(java.math.BigDecimal premiumRate) { this.premiumRate = premiumRate; }
    public java.math.BigDecimal getBrokerageRate() { return brokerageRate; }
    public void setBrokerageRate(java.math.BigDecimal brokerageRate) { this.brokerageRate = brokerageRate; }
    public java.math.BigDecimal getPremium() { return premium; }
    public void setPremium(java.math.BigDecimal premium) { this.premium = premium; }
    public java.math.BigDecimal getBrokerage() { return brokerage; }
    public void setBrokerage(java.math.BigDecimal brokerage) { this.brokerage = brokerage; }
    public java.math.BigDecimal getTotal() { return total; }
    public void setTotal(java.math.BigDecimal total) { this.total = total; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}

