package com.word.bank.backend.word.dto.word;

public record WordCreateRequest(
    String pronunciation,
    String name,
    String desc,
    String shortDesc,
    String synonyms,
    String antonyms
) {
}
