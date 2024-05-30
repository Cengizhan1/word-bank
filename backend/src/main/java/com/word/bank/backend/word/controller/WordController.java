package com.word.bank.backend.word.controller;

import com.word.bank.backend.word.service.WordService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/words")
public class WordController {

    private final WordService service;

    public WordController(WordService service) {
        this.service = service;
    }
}
