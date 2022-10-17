package ru.otus.ivan.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.ivan.model.mongo.BookMongo;
import ru.otus.ivan.model.sql.Book;
import ru.otus.ivan.repo.mongo.BookMongoRepo;
import ru.otus.ivan.repo.sql.BookH2Repo;

@ShellComponent
@RequiredArgsConstructor
public class ShellCommands {

    private final JobLauncher jobLauncher;
    private final Job job;
    private final BookMongoRepo bookMongoRepo;
    private final BookH2Repo bookH2Repo;

    @ShellMethod(value = "printBooks", key = "p")
    public void printBooks() {
        System.out.println("\n-------- Mongo --------");
        for (BookMongo bookMongo : bookMongoRepo.findAll()) {
            System.out.println(bookMongo);
        }
        System.out.println("\n-------- H2 --------");
        for (Book book : bookH2Repo.findAll()) {
            System.out.println(book);
        }
    }

    @ShellMethod(value = "convertDB", key = "c")
    public void startMigration() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        JobExecution execution = jobLauncher.run(job, new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis()).toJobParameters());
        System.out.println(execution);
    }
}
