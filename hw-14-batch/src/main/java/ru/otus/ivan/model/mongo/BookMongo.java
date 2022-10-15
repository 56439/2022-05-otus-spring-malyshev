package ru.otus.ivan.model.mongo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@Document(collection = "books")
public class BookMongo {

    @Id
    private String id;

    private String title;

    @DBRef
    private AuthorMongo author;

    @DBRef
    private GenreMongo genre;

    private List<CommentMongo> comments;
}
