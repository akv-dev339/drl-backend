package com.drl.drlwebsite.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Volume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private Integer year;
    private Boolean isCurrent;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "journal_id", nullable = false)
    @JsonBackReference
    private Journal journal;

    @JsonIgnore
    @OneToMany(mappedBy = "volume",
               cascade = CascadeType.ALL,
               orphanRemoval = true)
    private List<Article> articles;
}