package ru.otus.ivan.repo.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.ivan.model.mongo.GenreMongo;

public interface GenreMongoRepo extends MongoRepository<GenreMongo, String> {
}