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

    public SentenceDto create(SentenceCreateRequest request) {
        Word word = findWordById(request.wordId());
        List<Sentence> sentences = request.sentences().stream()
                .map(sentence -> Sentence.builder()
                        .sentence(sentence)
                        .word(word)
                        .build()).toList();
        List<Sentence> sentenceEntities = repository.saveAll(sentences);
        return SentenceDto.convert(sentenceEntities);
    }

    public SentenceDto getSentencesByWordId(Long wordId) {
        List<Sentence> sentences = repository.findAllByWordId(wordId);
        return SentenceDto.convert(sentences);
    }

    private Word findWordById(Long wordId) {
        return wordService.findWordById(wordId);
    }
}
