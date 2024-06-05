package com.word.bank.backend.word.controller;

import com.word.bank.backend.word.dto.word.WordCreateRequest;
import com.word.bank.backend.word.dto.word.WordDto;
import com.word.bank.backend.word.service.WordService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/words")
@Tag(name = "Word bank API v1", description = "Word API")
public class WordController {

    private final WordService service;

    public WordController(WordService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<WordDto> create(WordCreateRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping
    public ResponseEntity<List<WordDto>> getAllWordsByUserId() {
        return ResponseEntity.ok(service.getAllWordsByUserId());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WordDto> getWordById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getWordById(id));
    }
}
