package com.drl.drlwebsite.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String authors;

    private String articleType;   // Research, Review etc

    private String pages;         // pp. 1–12

    @Column(length = 5000)
    private String abstractText;

    private String pdfUrl;        // path to stored PDF

    @ManyToOne
    @JoinColumn(name = "volume_id", nullable = false)
    @JsonBackReference
    private Volume volume;
}
