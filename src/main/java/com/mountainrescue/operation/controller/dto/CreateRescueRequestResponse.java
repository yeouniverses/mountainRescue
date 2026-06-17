package com.mountainrescue.operation.controller.dto;

import java.time.OffsetDateTime;

public record CreateRescueRequestResponse(
        Integer missingPersonId,
        Integer rescueRequestId,
        String status,
        OffsetDateTime requestedAt
) {
}
