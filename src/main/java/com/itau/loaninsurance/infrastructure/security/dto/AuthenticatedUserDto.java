package com.itau.loaninsurance.infrastructure.security.dto;

import java.util.List;

public record AuthenticatedUserDto (
        String id,
        String username,
        List<String> roles
) {
}
