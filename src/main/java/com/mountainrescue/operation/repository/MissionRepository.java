package com.mountainrescue.operation.repository;

import com.mountainrescue.operation.repository.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Integer> {
}
