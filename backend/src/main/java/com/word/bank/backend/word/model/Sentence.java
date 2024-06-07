package com.word.bank.backend.word.model;

import com.word.bank.backend.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * This class holds example sentences that will be added to the words.
 */
@Data
@Entity
@Table(name = "sentences")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sentence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "word_id")
    private Word word;
    private String sentence;
}
