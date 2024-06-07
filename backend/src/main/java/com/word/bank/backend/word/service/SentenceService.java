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

    public List<SentenceDto> create(SentenceCreateRequest request) {
        List<Sentence> sentences = request.sentences().stream()
                .map(sentence -> Sentence.builder().sentence(sentence).build()).toList();
        return repository.saveAll(sentences).stream().map(SentenceDto::convert).toList();
    }

    public List<SentenceDto> getSentencesByWordId(Long wordId) {
        return repository.findAllByWordId(wordId).stream()
                .map(SentenceDto::convert)
                .toList();
    }
}
