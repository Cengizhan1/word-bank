package com.word.bank.backend.word.dto.sentence;

import java.util.List;

public record SentenceCreateRequest(
    List<String> sentences
) {
}
