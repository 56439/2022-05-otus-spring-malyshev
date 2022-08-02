package ru.otus.hw06library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@NamedEntityGraphs({
        @NamedEntityGraph(
            name = "book-only-entity-graph",
            attributeNodes = {
                    @NamedAttributeNode("author"),
                    @NamedAttributeNode("genre")
            }),
        @NamedEntityGraph(
            name = "book-comments-entity-graph",
            attributeNodes = {
                    @NamedAttributeNode("author"),
                    @NamedAttributeNode("genre"),
                    @NamedAttributeNode("comments")
            })
})
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    @ToString.Exclude
    private List<Comment> comments;

    public Book(String title, Author author, Genre genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public Book(Long id, String title, Author author, Genre genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
    }
}