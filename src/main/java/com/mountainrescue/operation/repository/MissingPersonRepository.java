package com.mountainrescue.operation.repository;

import com.mountainrescue.operation.repository.entity.MissingPerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissingPersonRepository extends JpaRepository<MissingPerson, Integer> {
}
