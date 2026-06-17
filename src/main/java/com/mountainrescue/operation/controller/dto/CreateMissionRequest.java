package com.mountainrescue.operation.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateMissionRequest {

    private Integer rescueRequestId;
    private Integer equipmentId;
    private Double simX;
    private Double simY;
    private Double simZ;
}
