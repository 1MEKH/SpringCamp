package com.ua.library.config;

import com.ua.library.domain.Author;
import com.ua.library.domain.Book;
import com.ua.library.domain.Genre;
import com.ua.library.domain.dto.AuthorDTO;
import com.ua.library.domain.dto.BookDTO;
import com.ua.library.domain.dto.GenreDTO;
import com.ua.library.processor.ConvertorIdProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.batch.item.data.builder.MongoItemReaderBuilder;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.sql.DataSource;
import java.util.HashMap;


@Configuration
public class JobConfig {

    private final DataSource dataSource;
    private final MongoTemplate template;
    private final ConvertorIdProcessor convertorIdProcessor;
    private final StepBuilderFactory stepBuilderFactory;
    private final JobBuilderFactory jobBuilderFactory;

    public JobConfig(DataSource dataSource, MongoTemplate template,
                     ConvertorIdProcessor convertorIdProcessor,
                     StepBuilderFactory stepBuilderFactory, JobBuilderFactory jobBuilderFactory) {
        this.dataSource = dataSource;
        this.template = template;
        this.convertorIdProcessor = convertorIdProcessor;
        this.stepBuilderFactory = stepBuilderFactory;
        this.jobBuilderFactory = jobBuilderFactory;
    }

    @Bean
    public MongoItemReader<Genre> mongoGenreItemReader(){
        return new MongoItemReaderBuilder<Genre>()
                .name("mongoGenreItemReader")
                .template(template)
                .jsonQuery("{}")
                .sorts(new HashMap<>())
                .targetType(Genre.class)
                .build();
    }

    @Bean
    public MongoItemReader<Author> mongoAuthorItemReader(){
        return new MongoItemReaderBuilder<Author>()
                .name("mongoAuthorItemReader")
                .template(template)
                .jsonQuery("{}")
                .sorts(new HashMap<>())
                .targetType(Author.class)
                .build();
    }

    @Bean
    public MongoItemReader<Book> mongoBookItemReader(){
        return new MongoItemReaderBuilder<Book>()
                .name("mongoBookItemReader")
                .template(template)
                .jsonQuery("{}")
                .sorts(new HashMap<>())
                .targetType(Book.class)
                .build();
    }

    @Bean
    public ItemProcessor<Genre, GenreDTO> genreItemProcessor(ConvertorIdProcessor convertorIdProcessor) {
        return convertorIdProcessor::updateGenreId;
    }

    @Bean
    public ItemProcessor<Author, AuthorDTO> authorItemProcessor(ConvertorIdProcessor convertorIdProcessor) {
        return convertorIdProcessor::updateAuthorId;
    }

    @Bean
    public ItemProcessor<Book, BookDTO> bookItemProcessor(ConvertorIdProcessor convertorIdProcessor) {
        return convertorIdProcessor::updateBookId;
    }

    @Bean
    public JdbcBatchItemWriter<GenreDTO> genreJdbcBatchItemWriter() {
        return new JdbcBatchItemWriterBuilder<GenreDTO>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO genres (id, type) VALUES (:id, :type)")
                .dataSource(dataSource)
                .build();
    }
    @Bean
    public JdbcBatchItemWriter<AuthorDTO> authorJdbcBatchItemWriter() {
        return new JdbcBatchItemWriterBuilder<AuthorDTO>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO authors (id, surname) VALUES (:id, :surname)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public JdbcBatchItemWriter<BookDTO> bookJdbcBatchItemWriter() {
        return new JdbcBatchItemWriterBuilder<BookDTO>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO books (id, tittle, author_id, genre_id) VALUES (:id, :tittle, :authorId, :genreId)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public Step genreStep() {
        return stepBuilderFactory.get("genreStep")
                .<Genre, GenreDTO>chunk(10)
                .reader(mongoGenreItemReader())
                .processor(genreItemProcessor(convertorIdProcessor))
                .writer(genreJdbcBatchItemWriter())
                .build();
    }

    @Bean
    public Step authorStep() {
        return stepBuilderFactory.get("authorStep")
                .<Author, AuthorDTO>chunk(10)
                .reader(mongoAuthorItemReader())
                .processor(authorItemProcessor(convertorIdProcessor))
                .writer(authorJdbcBatchItemWriter())
                .build();
    }

    @Bean
    public Step bookStep() {
        return stepBuilderFactory.get("bookStep")
                .<Book, BookDTO>chunk(10)
                .reader(mongoBookItemReader())
                .processor(bookItemProcessor(convertorIdProcessor))
                .writer(bookJdbcBatchItemWriter())
                .build();
    }

    @Bean
    public Job migrateMongoToMySql() {
        return jobBuilderFactory.get("migrateMongoToMySql")
                .start(genreStep())
                .next(authorStep())
                .next(bookStep())
                .build();
    }

}
