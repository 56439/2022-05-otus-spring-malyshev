package ru.otus.ivan.repo.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.ivan.model.mongo.AuthorMongo;

public interface AuthorMongoRepo extends MongoRepository<AuthorMongo, String> {
}