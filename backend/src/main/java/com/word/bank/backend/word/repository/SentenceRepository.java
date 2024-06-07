package com.word.bank.backend.word.repository;

import com.word.bank.backend.word.model.Sentence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SentenceRepository extends JpaRepository<Sentence, Long> {
    Optional<Sentence> findByWordId(Long wordId);
}
