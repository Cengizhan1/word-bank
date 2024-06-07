package com.word.bank.backend.word.service;

import com.word.bank.backend.word.dto.sentence.SentenceCreateRequest;
import com.word.bank.backend.word.dto.sentence.SentenceDto;
import com.word.bank.backend.word.model.Sentence;
import com.word.bank.backend.word.model.Word;
import com.word.bank.backend.word.repository.SentenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SentenceService {

    private final SentenceRepository repository;
    private final WordService wordService;

    public SentenceService(SentenceRepository repository, WordService wordService) {
        this.repository = repository;
        this.wordService = wordService;
    }

    public List<SentenceDto> create(SentenceCreateRequest request) {
        Word word = findWordById(request.wordId());
        List<Sentence> sentences = request.sentences().stream()
                .map(sentence -> Sentence.builder()
                        .sentence(sentence)
                        .word(word)
                        .build()).toList();
        return repository.saveAll(sentences).stream().map(SentenceDto::convert).toList();
    }

    public List<SentenceDto> getSentencesByWordId(Long wordId) {
        return repository.findAllByWordId(wordId).stream()
                .map(SentenceDto::convert)
                .toList();
    }

    private Word findWordById(Long wordId) {
        return wordService.findWordById(wordId);
    }
}
