package com.itau.loaninsurance.infrastructure.controller;

import com.itau.loaninsurance.application.*;
import com.itau.loaninsurance.application.models.QuoteCalculation;
import com.itau.loaninsurance.domain.models.Quote;
import com.itau.loaninsurance.application.models.QuoteInput;
import com.itau.loaninsurance.infrastructure.controller.dto.QuoteResponseDto;
import com.itau.loaninsurance.infrastructure.controller.mapper.QuoteMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/quotes")
public class QuoteController {

    private final CreateQuoteUseCase createUseCase;
    private final GetQuoteUseCase getUseCase;
    private final ListQuotesUseCase listUseCase;
    private final CalculateQuoteUseCase calculateQuoteUseCase;
    private final DeleteQuoteUseCase deleteQuoteUseCase;


    public QuoteController(CreateQuoteUseCase createUseCase,
                           GetQuoteUseCase getUseCase,
                           ListQuotesUseCase listUseCase, CalculateQuoteUseCase calculateQuoteUseCase, DeleteQuoteUseCase deleteQuoteUseCase) {
        this.createUseCase = createUseCase;
        this.getUseCase = getUseCase;
        this.listUseCase = listUseCase;
        this.calculateQuoteUseCase = calculateQuoteUseCase;
        this.deleteQuoteUseCase = deleteQuoteUseCase;
    }

    @PostMapping("/preview")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<QuoteCalculation> previewQuote(@Valid @RequestBody QuoteInput request) {
        QuoteCalculation created =  calculateQuoteUseCase.execute(request);
        return ResponseEntity.ok(created);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<QuoteResponseDto> create(@Valid @RequestBody QuoteInput request) {
        Quote created = createUseCase.execute(request);
        QuoteResponseDto dto = QuoteMapper.toResponse(created);
        return ResponseEntity.created(URI.create("/api/v1/quotes/" + created.getId())).body(dto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<QuoteResponseDto> get(@PathVariable UUID id) {
        Quote q = getUseCase.execute(id);
        return ResponseEntity.ok(QuoteMapper.toResponse(q));
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<QuoteResponseDto>> list() {
        var list = listUseCase.execute().stream().map(QuoteMapper::toResponse).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteQuoteUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}

