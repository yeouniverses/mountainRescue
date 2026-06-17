package com.mountainrescue.operation.repository;

import com.mountainrescue.operation.repository.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
