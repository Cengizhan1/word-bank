package com.word.bank.backend.word.repository;

import com.word.bank.backend.word.model.ListWord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListWordRepository extends JpaRepository<ListWord, Long> {
}
