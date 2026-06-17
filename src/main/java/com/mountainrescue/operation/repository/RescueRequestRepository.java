package com.mountainrescue.operation.repository;

import com.mountainrescue.operation.repository.entity.RescueRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RescueRequestRepository extends JpaRepository<RescueRequest, Integer> {
}
