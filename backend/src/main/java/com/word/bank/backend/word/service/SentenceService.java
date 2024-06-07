package com.word.bank.backend.word.service;

import com.word.bank.backend.word.dto.sentence.SentenceCreateRequest;
import com.word.bank.backend.word.dto.sentence.SentenceDto;
import com.word.bank.backend.word.model.Sentence;
import com.word.bank.backend.word.repository.SentenceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SentenceService {

    private final SentenceRepository repository;

    public SentenceService(SentenceRepository repository) {
        this.repository = repository;
    }

    public SentenceDto create(SentenceCreateRequest request) {
        Sentence sentence = Sentence.builder().sentence(request.sentence().toString()).build();
        return SentenceDto.convert(repository.save(sentence));
    }

    public SentenceDto getSentencesByWordId(Long wordId) {
        return SentenceDto.convert(repository.findByWordId(wordId).orElseThrow(
                () -> new EntityNotFoundException("There is no sentence with word id: " + wordId)
        ));
    }
}
