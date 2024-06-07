package com.word.bank.backend.word.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * This class is a list used to group words.
 */
@Data
@Entity
@Table(name = "list_words")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListWord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "list_id")
    private List list;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "word_id")
    private Word word;
}
