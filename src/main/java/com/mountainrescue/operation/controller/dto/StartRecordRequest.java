package com.mountainrescue.operation.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StartRecordRequest {

    private Integer equipmentId;
    private Integer missionId;
}
