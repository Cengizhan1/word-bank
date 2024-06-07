package com.word.bank.backend.word.controller;

import com.word.bank.backend.word.dto.sentence.SentenceCreateRequest;
import com.word.bank.backend.word.dto.sentence.SentenceDto;
import com.word.bank.backend.word.service.SentenceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/sentences")
@Tag(name = "Word bank API v1", description = "Sentence API")
public class SentenceController {

    private final SentenceService service;

    public SentenceController(SentenceService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SentenceDto> create(@RequestBody @Valid SentenceCreateRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping
    public ResponseEntity<SentenceDto> getSentencesByWordId(@RequestParam Long wordId) {
        return ResponseEntity.ok(service.getSentencesByWordId(wordId));
    }
}
