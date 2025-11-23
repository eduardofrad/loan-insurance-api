package com.itau.loaninsurance.infrastructure.persistence.springdata;

import com.itau.loaninsurance.infrastructure.persistence.entity.QuoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataQuoteRepository extends JpaRepository<QuoteEntity, String> {
}
