package com.mountainrescue.operation.controller.dto;

public record CreateMissionResponse(
        Integer missionId,
        Integer rescueRequestId,
        Integer equipmentId
) {
}
