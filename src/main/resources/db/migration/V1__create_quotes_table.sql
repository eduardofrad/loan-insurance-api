CREATE TABLE quotes (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    loan_amount DECIMAL(19,4) NOT NULL,
    term_months INT NOT NULL,
    premium_rate DECIMAL(19,8) NOT NULL,
    brokerage_rate DECIMAL(19,8) NOT NULL,
    premium DECIMAL(19,4),
    brokerage DECIMAL(19,4),
    total DECIMAL(19,4),
    created_at TIMESTAMP(6) NULL
);
