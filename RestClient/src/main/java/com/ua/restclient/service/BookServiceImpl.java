package com.ua.restclient.service;

import com.ua.restclient.domain.Book;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.logging.Logger;

@Service
public class BookServiceImpl implements BookService{

    private final RestTemplate restTemplate = new RestTemplate();
    private final static Logger logger = Logger.getLogger(BookService.class.getName());
    private final String BOOK_URL = "http://localhost:8080/book/";

    @Cacheable("books")
    @Retryable(value = Exception.class, maxAttempts = 5, backoff = @Backoff(delay = 5000))
    @Override
    public List<Book> findAll() {
        RequestEntity<Book> requestEntity = new RequestEntity<>(
                new Book(), HttpMethod.GET, URI.create(BOOK_URL));

        ResponseEntity<List<Book>> books = restTemplate.exchange(
                requestEntity,
                new ParameterizedTypeReference<>(){}
        );
        logger.info("[+] request all books");
        return books.getBody();
    }

    @Cacheable("books")
    @Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 3000))
    @Override
    public Book findById(long id) {
        logger.info("[+] request book by id " + id);
        return restTemplate.getForObject(BOOK_URL + id, Book.class);
    }
}
