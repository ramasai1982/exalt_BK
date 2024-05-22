package com.exalt.producerconsumer.repo;

import com.exalt.producerconsumer.model.Pass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassRepo extends JpaRepository<Pass, Long> {
}
