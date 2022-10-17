package ru.otus.ivan.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import ru.otus.ivan.converter.BookConverter;
import ru.otus.ivan.model.mongo.BookMongo;
import ru.otus.ivan.model.sql.Book;
import ru.otus.ivan.repo.mongo.BookMongoRepo;
import ru.otus.ivan.repo.sql.BookH2Repo;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class BookStepConfig {

    private static final int CHUNK_SIZE = 5;
    private static final String FIND_ALL = "findAll";
    private static final String SAVE = "save";

    private final StepBuilderFactory stepBuilderFactory;
    private final BookH2Repo bookH2Repo;
    private final BookMongoRepo bookMongoRepo;

    @Bean
    public ItemProcessor<Book, BookMongo> bookProcessor(BookConverter converter) {
        return converter::convert;
    }

    @Bean
    public RepositoryItemReader<Book> bookReader(){
        RepositoryItemReader<Book> reader = new RepositoryItemReader<>();
        reader.setRepository(bookH2Repo);
        reader.setMethodName(FIND_ALL);
        Map<String, Sort.Direction> sort = new HashMap<>();
        sort.put("id", Sort.Direction.ASC);
        reader.setSort(sort);
        return reader;
    }

    @Bean
    public RepositoryItemWriter<BookMongo> bookWriter() {
        RepositoryItemWriter<BookMongo> writer = new RepositoryItemWriter<>();
        writer.setRepository(bookMongoRepo);
        writer.setMethodName(SAVE);
        return writer;
    }

    @Bean(name="booksStep")
    public Step syncBooksStep(ItemProcessor bookProcessor,
                                RepositoryItemReader<Book> bookReader,
                                RepositoryItemWriter<BookMongo> writer) {
        return stepBuilderFactory.get("syncBooksStep")
                .chunk(CHUNK_SIZE)
                .reader(bookReader)
                .processor(bookProcessor)
                .writer(writer)
                .build();
    }
}
