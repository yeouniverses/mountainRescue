package com.mountainrescue.operation.repository;

import com.mountainrescue.operation.repository.entity.Camera;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CameraRepository extends JpaRepository<Camera, Integer> {
}
