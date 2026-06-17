package com.mountainrescue.operation.repository;

import com.mountainrescue.operation.repository.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {
}
