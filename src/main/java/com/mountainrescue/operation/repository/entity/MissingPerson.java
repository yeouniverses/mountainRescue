package com.mountainrescue.operation.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Entity
@Table(name = "missing_person", schema = "mountain_rescue")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MissingPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer age;

    private String gender;

    private String appearance;

    @Column(name = "last_known_location")
    private String lastKnownLocation;

    @Column(name = "sim_x")
    private Double simX;

    @Column(name = "sim_y")
    private Double simY;

    @Column(name = "sim_z")
    private Double simZ;

    @Column(name = "missing_since")
    private OffsetDateTime missingSince;

    private String notes;
}
