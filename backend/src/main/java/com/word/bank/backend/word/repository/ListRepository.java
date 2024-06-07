package com.word.bank.backend.word.repository;

import com.word.bank.backend.word.model.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListRepository extends JpaRepository<List, Long> {
}
