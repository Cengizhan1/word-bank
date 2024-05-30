package com.word.bank.backend.word.service;

import com.word.bank.backend.user.model.User;
import com.word.bank.backend.word.dto.word.WordCreateRequest;
import com.word.bank.backend.word.dto.word.WordDto;
import com.word.bank.backend.word.model.Word;
import com.word.bank.backend.word.repository.WordRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordService {

    private final WordRepository repository;

    public WordService(WordRepository wordRepository) {
        this.repository = wordRepository;
    }

    public WordDto create(WordCreateRequest request) {
        // TODO requestin içinden user id alınacak
        Word word = Word.builder().userId(1L)
                .pronunciation(request.pronunciation())
                .name(request.name())
                .desc(request.desc())
                .shortDesc(request.shortDesc())
                .synonyms(request.synonyms())
                .antonyms(request.antonyms())
                .build();
        repository.save(word);
        return WordDto.convert(word);
    }
    public List<WordDto> getAllWordsByUserId() {
        Long userId = 1L; // TODO requestin içinden user id alınacak
        return repository.findAllByUserId(userId).stream().map(WordDto::convert).toList();
    }

    public WordDto getWordById(Long id) {
        Long userId = 1L; // TODO requestin içinden user id alınacak
        return WordDto.convert(findWordById(id));
    }

    private Word findWordById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Word not found with id: " + id)
        );
    }
}
