package com.word.bank.backend.word.service;

import com.word.bank.backend.word.repository.WordRepository;
import org.springframework.stereotype.Service;

@Service
public class WordService {

    private final WordRepository repository;

    public WordService(WordRepository wordRepository) {
        this.repository = wordRepository;
    }
}
