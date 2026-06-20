package com.mountainrescue.operation.controller.dto;

import java.time.OffsetDateTime;

public record StartRecordResponse(
        Integer recordId,
        Integer equipmentId,
        Integer missionId,
        OffsetDateTime startTime
) {
}
