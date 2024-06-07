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
@Table(name = "lists")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class List {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
}
