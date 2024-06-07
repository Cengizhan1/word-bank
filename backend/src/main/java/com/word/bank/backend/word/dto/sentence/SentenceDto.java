package com.word.bank.backend.word.dto.sentence;

import com.word.bank.backend.word.model.Sentence;

import java.util.List;

public record SentenceDto(
        List<String> sentence
) {
    public static SentenceDto convert(List<Sentence> sentences) {
        return new SentenceDto(sentences.stream().map(Sentence::getSentence).toList());
    }
}
