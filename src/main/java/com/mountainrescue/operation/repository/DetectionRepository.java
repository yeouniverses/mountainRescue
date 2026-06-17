package com.mountainrescue.operation.repository;

import com.mountainrescue.operation.repository.entity.Detection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetectionRepository extends JpaRepository<Detection, Long> {
}
