package com.mountainrescue.operation.repository;

import com.mountainrescue.operation.repository.entity.Frame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FrameRepository extends JpaRepository<Frame, Long> {
}
