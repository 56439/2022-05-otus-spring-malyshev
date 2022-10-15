package ru.otus.ivan.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.stereotype.Component;
import ru.otus.ivan.model.mongo.AuthorMongo;
import ru.otus.ivan.model.mongo.BookMongo;
import ru.otus.ivan.model.mongo.CommentMongo;
import ru.otus.ivan.model.mongo.GenreMongo;
import ru.otus.ivan.model.sql.Author;
import ru.otus.ivan.model.sql.Book;
import ru.otus.ivan.model.sql.Comment;
import ru.otus.ivan.model.sql.Genre;
import ru.otus.ivan.repo.mongo.AuthorMongoRepo;
import ru.otus.ivan.repo.mongo.GenreMongoRepo;

import java.util.List;
import java.util.stream.Collectors;

@StepScope
@Component
@RequiredArgsConstructor
public class BookConverterImpl implements BookConverter {

    private final AuthorMongoRepo authorMongoRepo;
    private final GenreMongoRepo genreMongoRepo;

    @Override
    public BookMongo convert(Book book) {
        return new BookMongo(
                book.getId().toString(),
                book.getTitle(),
                convertAuthor(book.getAuthor()),
                convertGenre(book.getGenre()),
                convertComments(book.getComments()));
    }

    private AuthorMongo convertAuthor(Author author) {
        AuthorMongo authorMongo = new AuthorMongo(author.getId().toString(), author.getName());
        return authorMongoRepo.save(authorMongo);
    }

    private GenreMongo convertGenre(Genre genre) {
        GenreMongo genreMongo = new GenreMongo(genre.getId().toString(), genre.getName());
        return genreMongoRepo.save(genreMongo);
    }

    private List<CommentMongo> convertComments(List<Comment> comments) {
        return comments.stream()
                .map(comment -> new CommentMongo(comment.getId().toString(), comment.getEntry()))
                .collect(Collectors.toList());
    }
}