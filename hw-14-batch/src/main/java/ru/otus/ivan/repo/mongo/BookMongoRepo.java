package ru.otus.ivan.repo.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.ivan.model.mongo.BookMongo;

public interface BookMongoRepo extends MongoRepository<BookMongo, String> {
}
