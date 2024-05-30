package com.word.bank.backend.word.repository;

import com.word.bank.backend.word.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WordRepository extends JpaRepository<Word, Long> {

    List<Word> findAllByUserId(Long userId);
}
