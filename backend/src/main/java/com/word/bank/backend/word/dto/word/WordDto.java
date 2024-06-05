package com.word.bank.backend.word.dto.word;

import com.word.bank.backend.word.model.Word;

public record WordDto(
        String pronunciation,
        String name,
        String desc,
        String shortDesc,
        String synonyms,
        String antonyms
) {
    public static WordDto convert(Word word) {
        return new WordDto(
                word.getPronunciation(),
                word.getName(),
                word.getDescription(),
                word.getShortDesc(),
                word.getSynonyms(),
                word.getAntonyms()
        );
    }
}
