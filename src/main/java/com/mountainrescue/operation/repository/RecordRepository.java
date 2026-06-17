package com.mountainrescue.operation.repository;

import com.mountainrescue.operation.repository.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record, Integer> {
}
