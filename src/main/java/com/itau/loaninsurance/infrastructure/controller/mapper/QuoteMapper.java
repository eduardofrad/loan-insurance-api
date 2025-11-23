package com.itau.loaninsurance.infrastructure.controller.mapper;


import com.itau.loaninsurance.domain.models.Quote;
import com.itau.loaninsurance.infrastructure.persistence.entity.QuoteEntity;
import com.itau.loaninsurance.infrastructure.controller.dto.QuoteResponseDto;

public final class QuoteMapper {
    private QuoteMapper() {}

    public static QuoteResponseDto toResponse(Quote q) {
        return new QuoteResponseDto(
                q.getId(),
                q.getLoanAmount(),
                q.getTermMonths(),
                q.getPremiumRate(),
                q.getBrokerageRate(),
                q.getPremium(),
                q.getBrokerage(),
                q.getTotal(),
                q.getCreatedAt()
        );
    }

    public static QuoteEntity toEntity(Quote q) {
        return new QuoteEntity(
                q.getId().toString(),
                q.getLoanAmount(),
                q.getTermMonths(),
                q.getPremiumRate(),
                q.getBrokerageRate(),
                q.getPremium(),
                q.getBrokerage(),
                q.getTotal(),
                q.getCreatedAt()
        );
    }

    public static Quote toDomain(QuoteEntity e) {
        return new Quote(
                java.util.UUID.fromString(e.getId()),
                e.getLoanAmount(),
                e.getTermMonths(),
                e.getPremiumRate(),
                e.getBrokerageRate(),
                e.getPremium(),
                e.getBrokerage(),
                e.getTotal(),
                e.getCreatedAt()
        );
    }
}

