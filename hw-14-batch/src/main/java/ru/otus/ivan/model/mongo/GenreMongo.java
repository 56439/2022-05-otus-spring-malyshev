package ru.otus.ivan.model.mongo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Data
@ToString
@AllArgsConstructor
@Document(collection = "genres")
public class GenreMongo {

    @Id
    private String id;

    private String name;
}