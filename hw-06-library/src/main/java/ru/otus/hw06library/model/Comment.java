package ru.otus.hw06library.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@NoArgsConstructor
@ToString
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "entry", nullable = false)
    private String entry;

    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public Comment(String entry, Book book) {
        this.entry = entry;
        this.book = book;
    }
}