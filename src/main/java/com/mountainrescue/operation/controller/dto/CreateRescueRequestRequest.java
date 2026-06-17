package com.mountainrescue.operation.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CreateRescueRequestRequest {

    private MissingPersonRequest missingPerson;
    private String reporterType;
    private Double simX;
    private Double simY;
    private Double simZ;
    private String notes;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class MissingPersonRequest {
        private String name;
        private Integer age;
        private String gender;
        private String appearance;
        private String lastKnownLocation;
        private Double simX;
        private Double simY;
        private Double simZ;
        private OffsetDateTime missingSince;
        private String notes;
    }
}
