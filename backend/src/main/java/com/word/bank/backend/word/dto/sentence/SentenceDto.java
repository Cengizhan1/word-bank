package com.word.bank.backend.word.dto.sentence;

import com.word.bank.backend.word.model.Sentence;

public record SentenceDto(
        String sentence
) {
    public static SentenceDto convert(Sentence sentence) {
        return new SentenceDto(sentence.getSentence());
    }
}
