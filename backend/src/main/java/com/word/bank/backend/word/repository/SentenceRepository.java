package com.word.bank.backend.word.repository;

import com.word.bank.backend.word.model.Sentence;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SentenceRepository extends JpaRepository<Sentence, Long> {
    List<Sentence> findAllByWordId(Long wordId);
}
